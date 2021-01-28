package com.me.blip.boxtest;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import java.util.ArrayList;
import java.util.HashMap;

public class Buoyancy2D {
    //Key - Object
    //Value - Fluid
    //Note: Works under the impression that an object will never touch 2 different bodies of fluid at the same time!
    //(Only the object can be identified through the use of a hash map)
    private HashMap<Fixture,Fixture> objectFluidPairs = new HashMap<Fixture, Fixture>();
    private Vector2 gravity;
    //For use with utility functions
    ArrayList<Vector2> subject = new ArrayList<Vector2>();
    ArrayList<Vector2> clipper = new ArrayList<Vector2>();
    ArrayList<Vector2> result = new ArrayList<Vector2>();

    public Buoyancy2D(Vector2 gravity)
    {
        this.gravity = gravity;
    }

    public void update(){
        for(HashMap.Entry<Fixture,Fixture> entry : objectFluidPairs.entrySet()) {
            ArrayList<Vector2> intersectionPoints = findIntersectionOfFixtures(entry.getKey(), entry.getValue());
            if (intersectionPoints.size() > 2) {
                float area = computeArea(intersectionPoints);
                Vector2 centroid = computeCentroid(intersectionPoints, area);
                //apply buoyancy force (entry.getValue is the fluid!)
                float displacedMass = entry.getValue().getDensity() * area;
                Vector2 buoyancyForce = gravity.cpy().scl(-displacedMass);
                entry.getKey().getBody().applyForce(buoyancyForce, centroid, false);
                for (int i = 0; i < intersectionPoints.size(); i++) {
                    Vector2 v0 = intersectionPoints.get(i);
                    Vector2 v1 = intersectionPoints.get((i + 1) % intersectionPoints.size());
                    Vector2 midPoint = (v0.cpy().add(v1)).scl(0.5f);
                    //find relative velocity between obect and fluid at edge midpoint
                    Vector2 velDir = entry.getKey().getBody().getLinearVelocityFromWorldPoint(midPoint).sub(
                            entry.getValue().getBody().getLinearVelocityFromWorldPoint(midPoint));
                    float vel = velDir.len();
                    velDir.nor();
                    Vector2 edge = v1.cpy().sub(v0);
                    float edgeLength = edge.len();
                    edge.nor();
                    //gets perpendicular to edge!
                    //might be in the wrong direction?
                    Vector2 normal = new Vector2(-edge.y, edge.x);
                    float dragDot = normal.dot(velDir);
                    //normal points wrong way so no drag
                    if (dragDot < 0) continue;
                    //Apply Drag
                    float dragMag = dragDot * edgeLength * entry.getValue().getDensity() * vel * vel;
                    Vector2 dragForce = velDir.cpy().scl(-dragMag);
                    entry.getKey().getBody().applyForce(dragForce, midPoint, false);
                    //Apply Lift
                    float liftDot = edge.dot(velDir);
                    float liftMag = dragDot * liftDot * edgeLength * entry.getValue().getDensity() * vel * vel;
                    Vector2 liftDir = new Vector2(velDir.y, -velDir.x);
                    Vector2 liftForce = liftDir.scl(liftMag);
                    entry.getKey().getBody().applyForce(liftForce, midPoint, false);
                }

                //Apply simple angular drag
                float angularDrag = area * -entry.getKey().getBody().getAngularVelocity();
                entry.getKey().getBody().applyTorque(angularDrag, false);
            }
        }
    }

    public void addObjectFluidPair(Fixture object,Fixture fluid)
    {
        objectFluidPairs.put(object,fluid);
    }

    public void removeObjectFluidPair(Fixture object)
    {
        objectFluidPairs.remove(object);
    }



    //puts intersection vertices in outputvertices and returns whether intersection exists or not
    public ArrayList<Vector2> findIntersectionOfFixtures(Fixture fa, Fixture fb)
    {
        subject.clear();
        result.clear();
        clipper.clear();

        ArrayList<Vector2>faVertices = new ArrayList<Vector2>();
        ArrayList<Vector2>fbVertices = new ArrayList<Vector2>();
        PolygonShape faShape = (PolygonShape) fa.getShape();
        PolygonShape fbShape = (PolygonShape) fb.getShape();

        Vector2 tmp = new Vector2();

        for(int i=0;i<faShape.getVertexCount();i++)
        {
            faShape.getVertex(i,tmp);
            faVertices.add(new Vector2(fa.getBody().getWorldPoint(tmp)));
        }

        for(int i=0;i<fbShape.getVertexCount();i++)
        {
            fbShape.getVertex(i,tmp);
            fbVertices.add(new Vector2(fb.getBody().getWorldPoint(tmp)));
        }

        doIntersection(faVertices,fbVertices);

        return result;
    }

    public void doIntersection(ArrayList<Vector2> faPoints,ArrayList<Vector2> fbPoints)
    {
        ArrayList<Vector2> subjPoints = faPoints;
        ArrayList<Vector2> clipPoints = fbPoints;

        subject = subjPoints;
        result = subject;
        clipper = clipPoints;

        clipPolygon();
    }

    private void clipPolygon()
    {
        int len = clipper.size();
        for (int i = 0; i < len; i++)
        {

            int len2 = result.size();
            ArrayList<Vector2> input = result;
            result = new ArrayList<Vector2>(len2);

            Vector2 A = clipper.get((i + len - 1) % len);
            Vector2 B = clipper.get(i);

            for (int j = 0; j < len2; j++)
            {

                Vector2 P = input.get((j + len2 - 1) % len2);
                Vector2 Q = input.get(j);

                if (isInside(A, B, Q))
                {
                    if (!isInside(A, B, P))
                        result.add(intersection(A, B, P, Q));
                    result.add(Q);
                }
                else if (isInside(A, B, P))
                    result.add(intersection(A, B, P, Q));
            }
        }
    }

    private boolean isInside(Vector2 a, Vector2 b, Vector2 c)
    {
        return (a.x - c.x) * (b.y - c.y) > (a.y - c.y) * (b.x - c.x);
    }

    private Vector2 intersection(Vector2 a, Vector2 b, Vector2 p, Vector2 q)
    {
        float A1 = b.y - a.y;
        float B1 = a.x - b.x;
        float C1 = A1 * a.x + B1 * a.y;

        float A2 = q.y - p.y;
        float B2 = p.x - q.x;
        float C2 = A2 * p.x + B2 * p.y;

        float det = A1 * B2 - A2 * B1;
        float x = (B2 * C1 - B1 * C2) / det;
        float y = (A1 * C2 - A2 * C1) / det;

        return new Vector2(x, y);
    }

    public Vector2 computeCentroid(ArrayList<Vector2> vertices,float area)
    {
        float cx6A = 0f;
        float cy6A = 0f;

        for(int i=0;i<vertices.size();i++)
        {
            float xi = vertices.get(i).x;
            float xi1 = i + 1 == vertices.size() ?  vertices.get(0).x : vertices.get(i+1).x;
            float yi = vertices.get(i).y;
            float yi1 = i+1 == vertices.size() ? vertices.get(0).y : vertices.get(i+1).y;

            cx6A += (xi + xi1) * (xi * yi1 - xi1 * yi);
            cy6A += (yi + yi1) * (xi * yi1 - xi1 * yi);
        }

        return new Vector2(cx6A / 6f / area,cy6A / 6f / area);
    }


    public float computeArea(ArrayList<Vector2> vertices)
    {
        float doubleArea = 0;

        for(int i=0;i<vertices.size();i++)
        {
            float xi = vertices.get(i).x;
            float xi1 = i + 1 == vertices.size() ?  vertices.get(0).x : vertices.get(i+1).x;
            float yi = vertices.get(i).y;
            float yi1 = i+1 == vertices.size() ? vertices.get(0).y : vertices.get(i+1).y;

            doubleArea += xi * yi1 - xi1 * yi;
        }

        return doubleArea * 0.5f;
    }
}

#include<cmath>

#include "Sphere.hpp"


using namespace rt;

Intersection Sphere::getIntersection(const Line& line,
                                     double minDist,
                                     double maxDist) {
    Intersection intersection;

    double b = 2 * (line.dx().x()*(line.x0().x() - center().x()) + line.dx().y()*(line.x0().y()
                - center().y()) + line.dx().z()*(line.x0().z() - center().z()));
    double c = (line.x0().x() - center().x())*(line.x0().x() - center().x()) + (line.x0().y()
                - center().y())*(line.x0().y() - center().y()) + (line.x0().z() - center().z())*(line.x0().z()
                - center().z()) - radius()*radius();

    double delta = b * b - 4 * c;
    double  t0, t1, t = -1;

    if (delta < 0) {
        return intersection;
    }
    if (delta == 0){
        t0 = (-1 * b + sqrt(delta)) / 2.0;
        if (t0 >= 0){
            t = t0;
        }
    } else {
        t0 = (-1 * b + sqrt(delta)) / 2.0;
        t1 = (-1 * b - sqrt(delta)) / 2.0;
        if (t0 < t1){
            t = t0;
        } else {
            t = t1;
        }
    }

    if (t >= minDist && t <= maxDist) {
        Intersection inter(true, *this, line, t);
        return inter;
    }

    return intersection;
}


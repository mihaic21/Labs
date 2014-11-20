#include "Vector.hpp"
#include "Line.hpp"
#include "Intersection.hpp"
#include "Geometry.hpp"
#include "Color.hpp"

#ifndef RT_SPHERE_INCLUDED
#define RT_SPHERE_INCLUDED

namespace rt {

  class Sphere : public Geometry {
  private:
    Vector _center;
    double _radius;

  public:
    Sphere(const Vector& center, double radius, const Color& color) 
    : Geometry(color) {
      _center = Vector(center);
      _radius = radius;
    }

    Sphere(double x, double y, double z, double radius,
           double r, double g, double b) 
    : Geometry (Color(r, g, b)){
      _center = Vector(x, y, z);
      _radius = radius;
    }

    virtual Intersection getIntersection(const Line& line, 
                                         double minDist,
                                         double maxDist);

    inline double& radius() {
      return _radius;
    }

    inline Vector& center() {
      return _center;
    }
  };
}

#endif

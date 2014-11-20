#include "Color.hpp"
#include "Vector.hpp"
#include "Line.hpp"
#include "Intersection.hpp"

#ifndef RT_GEOMETRY_INCLUDED
#define RT_GEOMETRY_INCLUDED

namespace rt {

  class Geometry {
  private:
    Color _color;

  public:
    Geometry(const Color& color) {
      _color = Color(color);
    }

    Geometry(const Geometry& geometry) {
      _color = Color(geometry.color());
    }

    virtual Intersection getIntersection(const Line& line, 
                                         double minDist,
                                         double maxDist) {
      Intersection in(false, *this, line, 0);
      return in;
    };

    inline Color color() const {
      return _color;
    }
  };
}

#endif

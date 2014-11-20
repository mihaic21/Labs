#include "Intersection.hpp"
#include "Geometry.hpp"
#include "Line.hpp"

using namespace rt;

Intersection::Intersection(bool b, const Geometry& geometry, 
			   const Line& line, double t) 
  : _geometry(&geometry), _line(&line) {
  _valid = b;
  _t = t;
  _vec = line.vec(t);
}
    
Intersection::Intersection()
  : _geometry(NULL), _line(NULL) {
  _valid = false;
  _t = 0;
}

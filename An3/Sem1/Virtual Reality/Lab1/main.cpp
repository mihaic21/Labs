#include <cmath>
#include <iostream>
#include <string>

#include "Vector.hpp"
#include "Line.hpp"
#include "Geometry.hpp"
#include "Sphere.hpp"
#include "Image.hpp"
#include "Color.hpp"
#include "Intersection.hpp"
#include "Material.hpp"

#include "Scene.hpp"

using namespace std;
using namespace rt;

double imageToViewPlane(int n, int imgSize, double viewPlaneSize) {
  double u = (double)n*viewPlaneSize/(double)imgSize;
  u -= viewPlaneSize/2;
  return u;
}

const Intersection findFirstIntersection(const Line& ray,
                                         double minDist, double maxDist) {
  Intersection intersection;

  for(int i=0; i<geometryCount; i++) {
    Intersection in = scene[i]->getIntersection(ray, minDist, maxDist);
    if(in.valid()) {
      if(!intersection.valid()) {
        intersection = in;
      }
      else if(in.t() < intersection.t()) {
        intersection = in;
      }
    }
  }

  return intersection;
}

int main() {

    Vector viewPoint(0, 0, 0);
    Vector viewDirection(0, 0, 1);
    Vector viewUp(0, -1, 0);

    double frontPlaneDist = 2;
    double backPlaneDist = 1000;

    double viewPlaneDist = 100;
    double viewPlaneWidth = 200;
    double viewPlaneHeight = 100;

    int imageWidth = 1000;
    int imageHeight = 500;

    Vector viewParallel = viewUp^viewDirection;

    viewDirection.normalize();
    viewUp.normalize();
    viewParallel.normalize();

    Image image(imageWidth, imageHeight);

    Color lightAmbient(1, 1, 1);
    Color lightDiffuse(1, 1, 1);
    Color lightSpecular(1, 1, 1);
    Material lightMaterial(lightAmbient, lightDiffuse, lightSpecular, 1, 1, 1);

    Vector light(300, 200, 0);

    Color ambient(0.1, 0.1, 0.1);
    Color diffuse(0.4, 0.4, 0.4);
    Color specular(0.6, 0.6, 0.6);
    Material sphereMaterial(ambient, diffuse, specular, 100, 1, 1);

    for (int i = 0; i < imageWidth; i++){

        for (int j = 0; j < imageHeight; j++){

            Vector x1 = viewPoint + viewDirection*viewPlaneDist +
                        viewUp*imageToViewPlane(j, imageHeight, viewPlaneHeight) +
                        viewParallel*imageToViewPlane(i, imageWidth, viewPlaneWidth);
            Line line(viewPoint, x1, false);
            Intersection firstIntersection = findFirstIntersection(line, frontPlaneDist, backPlaneDist);

            if (firstIntersection.valid()) {

                //T = vector from the intersection point to the light (normalized)
                Vector T = light - firstIntersection.vec();
                T.normalize();

                //N =  normal to the surface at the intersection point (normalized)
                Vector N = firstIntersection.vec() - ((Sphere*)&firstIntersection.geometry())->center();
                N.normalize();

                //E = vector from the intersection point to the camera (normalized)
                Vector E = viewPoint - firstIntersection.vec();
                E.normalize();

                //R = reflection vector (normalized)
                Vector R = N*(N*T)*2-T;
                R.normalize();

                Color color = sphereMaterial.ambient()*lightMaterial.ambient()*firstIntersection.geometry().color();
                if (N*T > 0){
                    color += sphereMaterial.diffuse()*lightMaterial.diffuse()*(N*T) *firstIntersection.geometry().color();
                }
                if (E*R > 0){
                    color += sphereMaterial.specular()*lightMaterial.specular()*pow(E*R, sphereMaterial.shininess())*firstIntersection.geometry().color();
                }

                image.setPixel(i, j, color);
            }

        }

    }

    image.store("scene.ppm");

    for(int i=0; i<geometryCount; i++) {
        delete scene[i];
    }

    return 0;
}

#include <iostream>
#include <fstream>
#include "Color.hpp"
#include <string>

#ifndef RT_IMAGE_INCLUDED
#define RT_IMAGE_INCLUDED

namespace rt {

  class Image {
  private:
    int _width;
    int _height;
    unsigned char *buffer;

  public:
    Image(int width, int height) {
      _width = width;
      _height = height;

      buffer = new unsigned char[3*_width*_height];
      for(int i=0; i<3*_width*_height; i++) {
        buffer[i] = 0;
      }
    }

    void setPixel(int x, int y, const Color& c) {
      double p;
      int k = 3*(y*_width+x);

      p = ceil(c.red()*255);
      if(p > 255) {
        p = 255;
      }
      buffer[k] = (unsigned char)p;

      p = ceil(c.green()*255);
      if(p > 255) {
        p = 255;
      }
      buffer[k+1] = (unsigned char)p;

      p = ceil(c.blue()*255);
      if(p > 255) {
        p = 255;
      }
      buffer[k+2] = (unsigned char)p;
    }

    void store(std::string fn) {
      std::ofstream out (fn.c_str(), std::ios::out | std::ios::binary);

      out << "P6" << std::endl;
      out << _width << " " << _height << std::endl;
      out << 255 << std::endl;

      out.write((char*)buffer, 3*_width*_height);

      out.close();
    }
  };
}

#endif

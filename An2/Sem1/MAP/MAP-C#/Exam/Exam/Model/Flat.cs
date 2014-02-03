using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Exam.Model
{
    class Flat : Property
    {
        public int noOfRooms;

        public Flat() { }

        public Flat(int surface, String zone, int noOfRooms)
            : base(surface, zone)
        {
            this.noOfRooms = noOfRooms;
        }

        public String ToString()
        {
            return this.GetType().Name + ";" + this.noOfRooms.ToString() + ";" + this.surface.ToString() + ";" + this.zone + ";";
        }

    }
}

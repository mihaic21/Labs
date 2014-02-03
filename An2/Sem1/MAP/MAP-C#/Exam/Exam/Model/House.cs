using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Exam.Model
{
    class House : Property
    {
        public int noOfRooms;
        public int noOfFloors;

        public House() { }

        public House(int surface, String zone, int noOfRooms, int noOfFloors)
            : base(surface, zone)
        {
            this.noOfFloors = noOfFloors;
            this.noOfRooms = noOfRooms;
            this.zone = zone;
        }

        public String ToString()
        {
            return this.GetType().Name + ";" + this.noOfFloors.ToString() + ";" + this.noOfRooms.ToString() + ";" + this.surface.ToString() + ";" + this.zone + ";";
        }

    }
}

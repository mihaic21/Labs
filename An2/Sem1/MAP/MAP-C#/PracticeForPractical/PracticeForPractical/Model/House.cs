using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PracticeForPractical.Model
{
    class House : Property
    {
        public int noOfRooms;
        public int noOfFloors;
        public int zone;

        public House(){}

        public House(int id, int surface, int rentalPrice, int noOfRooms, int noOfFloors, int zone)
            : base(id, surface, rentalPrice)
        {
            this.noOfFloors = noOfFloors;
            this.noOfRooms = noOfRooms;
            this.zone = zone;
        }



    }
}

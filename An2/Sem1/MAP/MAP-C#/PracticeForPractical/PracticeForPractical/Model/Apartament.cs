using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PracticeForPractical.Model
{
    class Apartament : Property
    {
        public int noOfRooms;

        public Apartament() { }

        public Apartament(int id, int surface, int rentalPrice, int noOfRooms)
            : base(id, surface, rentalPrice)
        {
            this.noOfRooms = noOfRooms;
        }
    }
}

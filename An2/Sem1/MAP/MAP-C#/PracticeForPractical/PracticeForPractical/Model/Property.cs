using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PracticeForPractical.Model
{
    class Property : HasID
    {
        private int id;
        public int surface;
        public int rentalPrice;

        public Property()
        {
        }

        public Property(int id, int surface, int rentalPrice)
        {
            this.id = id;
            this.surface = surface;
            this.rentalPrice = rentalPrice;
        }

        public int getID()
        {
            return this.id;
        }
    }
}

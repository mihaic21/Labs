using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PracticeForPractical.Model
{
    class CommercialSpace : Property
    {
        public CommercialSpace(){}

        public CommercialSpace(int id, int surface, int rentalPrice)
            : base(id, surface, rentalPrice)
        {}
    }
}

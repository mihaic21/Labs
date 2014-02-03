using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Exam.Model
{
    class CommercialSpace : Property
    {
        public CommercialSpace() { }

        public CommercialSpace(int surface, String zone) : base(surface, zone) { }

        public String ToString()
        {
            return this.GetType().Name + ";" + this.surface.ToString() + ";" + this.zone + ";";
        }

    }
}

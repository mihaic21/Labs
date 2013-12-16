using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Partial.Model
{
    class Leguma
    {
        private String name;
        private float weight;

        public Leguma(String name, float weight)
        {
            this.name = name;
            this.weight = weight;
        }

        public String getMyClass()
        {
            return this.GetType().Name;
        }

        public float getWeight()
        {
            return this.weight;
        }

        public String toString()
        {
            return this.getMyClass() + " " + this.name + " " + this.weight;
        }

    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Exam.Model
{
    class Transaction
    {
        //private int id;
        public Property property;
        public int year;
        public int month;

        public Transaction(Property property, int year, int month)
        {
            this.property = property;
            this.year = year;
            this.month = month;
        }


        public String ToString()
        {
            return property.ToString() + ";" + this.year.ToString() + ";" + this.month.ToString();
        }

/*
        public int getID()
        {
            return this.id;
        }*/
    }
}

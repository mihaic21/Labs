using PracticeForPractical.Model;
using PracticeForPractical.Repository;
using PracticeForPractical.Utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PracticeForPractical.Controller
{
    class Validator
    {
        private RepoInterface<Property> repo;
        public Validator(RepoInterface<Property> repo)
        {
            this.repo = repo;
        }

        private bool propertyExists(int id)
        {
            IDictionary<int, Property> properties = repo.getAllElements();

            foreach (Property property in properties.Values)
            {
                if (property.getID() == id)
                {
                    return true;
                }
            }

            return false;
        }

        public void validateProperty(Property property)
        {
            if (property.rentalPrice < 0)
            {
                throw new MyException("Rental Price must be greater than 0!");
            }

            if (this.propertyExists(property.getID()))
            {
                throw new MyException("Property ID already exists!");
            }
        }
    }
}

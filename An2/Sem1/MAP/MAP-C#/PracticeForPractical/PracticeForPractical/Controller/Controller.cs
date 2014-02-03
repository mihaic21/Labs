using PracticeForPractical.Model;
using PracticeForPractical.Repository;
using PracticeForPractical.Utils;
using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PracticeForPractical.Controller
{
    class Controller
    {
        private RepoInterface<Property> repo;
        private Validator val;

        public Controller(RepoInterface<Property> repo, Validator val)
        {
            this.repo = repo;
            this.val = val;
        }

        public void addProperty(int id, int surface, int rentalPrice)
        {
            CommercialSpace property = new CommercialSpace(id, surface, rentalPrice);
            this.val.validateProperty(property);
            repo.addElement(property);
        }

        public void addProperty(int id, int surface, int rentalPrice, int noOfRooms)
        {
            Apartament property = new Apartament(id, surface, rentalPrice, noOfRooms);
            this.val.validateProperty(property);
            repo.addElement(property);
        }

        public void addProperty(int id, int surface, int rentalPrice, int noOfRooms, int noOfFloors, int zone)
        {
            House property = new House(id, surface, rentalPrice, noOfRooms, noOfFloors, zone);
            this.val.validateProperty(property);
            repo.addElement(property);
        }

        public IList<Property> getAllPropertyObjects()
        {
            IDictionary<int, Property> properties = this.repo.getAllElements();
            List<Property> list = new List<Property>();

            foreach (Property property in properties.Values)
            {
                list.Add(property);
            }

            return list;
        }

        public void saveStudentsInFile(String fileName)
        {
            repo.writeToFile(fileName);
        }

        public void readFromFile(String fileName)
        {
            StreamReader sr = new StreamReader(fileName);
            String line;
            String[] tokens;

            IDictionary<int, Property> newDict = new Dictionary<int, Property>();

            while ((line = sr.ReadLine()) != null)
            {
                tokens = line.Split(new String[] { " " }, StringSplitOptions.None);

                if ("Apartament".Equals(tokens[0]))
                {
                    newDict.Add(Convert.ToInt32(tokens[1]), new Apartament(Convert.ToInt32(tokens[1]), Convert.ToInt32(tokens[2]), Convert.ToInt32(tokens[3]), Convert.ToInt32(tokens[4])));
                }
                else if ("House".Equals(tokens[0]))
                {
                    newDict.Add(Convert.ToInt32(tokens[1]), new House(Convert.ToInt32(tokens[1]), Convert.ToInt32(tokens[2]), Convert.ToInt32(tokens[3]), Convert.ToInt32(tokens[4]), Convert.ToInt32(tokens[5]), Convert.ToInt32(tokens[6])));
                }
                else if ("CommercialSpace".Equals(Convert.ToInt32(tokens[0])))
                {
                    newDict.Add(Convert.ToInt32(tokens[1]), new CommercialSpace(Convert.ToInt32(tokens[1]), Convert.ToInt32(tokens[2]), Convert.ToInt32(tokens[3])));
                }
                else
                {
                    throw new MyException("Error reading from file!");
                }
            }

            repo.replaceContent(newDict);
            sr.Close();
        }

    }
}

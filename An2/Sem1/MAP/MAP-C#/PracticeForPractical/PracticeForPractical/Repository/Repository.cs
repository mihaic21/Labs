using PracticeForPractical.Model;
using PracticeForPractical.Utils;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PracticeForPractical.Repository
{
    class Repository<T> : RepoInterface<T> where T : HasID
    {
        private IDictionary<int, T> elements = new Dictionary<int, T>();

        public Repository()
        { }

        public Repository(IDictionary<int, T> adt)
        {
            this.elements = adt;
        }

        public void addElement(T element)
        {
            this.elements.Add(element.getID(), element);
        }

        public IDictionary<int, T> getAllElements()
        {
            return new Dictionary<int, T>(this.elements);
        }

        public void replaceContent(IDictionary<int, T> adt)
        {
            this.elements.Clear();
            this.elements = adt;
        }

        public void writeToFile(String fileName)
        {
            try
            {
                StreamWriter writer = new StreamWriter(fileName);
                IDictionary<int, T> temp = this.getAllElements();
                String className;
                foreach (T element in temp.Values)
                {
                    className = element.GetType().Name;
                    writer.WriteLine(className + " " + element.ToString());
                }
                writer.Close();
            }
            catch (IOException)
            {
                throw new MyException("Problems in writing to file!");
            }
        }
    }
}

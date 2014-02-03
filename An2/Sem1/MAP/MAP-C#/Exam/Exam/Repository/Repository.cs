using Exam.Model;
using Exam.Utils;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Exam.Repository
{
    class Repository<T> : RepoInterface<T>
    {
        private List<T> elements = new List<T>();

        public Repository()
        { }

        public Repository(List<T> adt)
        {
            this.elements = adt;
        }

        public void addElement(T element)
        {
            this.elements.Add(element);
        }

        public List<T> getAllElements()
        {
            return new List<T>(this.elements);
        }

        public void replaceContent(List<T> adt)
        {
            this.elements.Clear();
            this.elements = adt;
        }

        public void writeToFile(String fileName)
        {
            try
            {
                StreamWriter writer = new StreamWriter(fileName);
                List<T> temp = this.getAllElements();
                //String className;
                foreach (T element in temp)
                {
                    //className = element.GetType().Name;
                    writer.WriteLine(element.ToString());
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

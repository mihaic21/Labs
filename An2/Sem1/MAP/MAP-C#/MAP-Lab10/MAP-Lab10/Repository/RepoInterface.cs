using MAP_Lab10.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MAP_Lab10.Repository
{
    interface RepoInterface<T>
    {
        void addElement(T element);
        IDictionary<int, T> getAllElements();
        void replaceContent(IDictionary<int, T> adt);
        void writeToFile(String fileName);

    }
}

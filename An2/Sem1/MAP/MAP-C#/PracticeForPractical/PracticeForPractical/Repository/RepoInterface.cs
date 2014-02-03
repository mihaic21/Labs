using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PracticeForPractical.Repository
{
    interface RepoInterface<T>
    {
        void addElement(T element);
        IDictionary<int, T> getAllElements();
        void replaceContent(IDictionary<int, T> adt);
        void writeToFile(String fileName);

    }
}

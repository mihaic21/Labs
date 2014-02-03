using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Exam.Repository
{
    interface RepoInterface<T>
    {
        void addElement(T element);
        List<T> getAllElements();
        void replaceContent(List<T> adt);
        void writeToFile(String fileName);

    }
}

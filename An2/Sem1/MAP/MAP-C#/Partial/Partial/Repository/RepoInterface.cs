using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Partial.Repository
{
    interface RepoInterface<T>
    {
        LinkedList<T> getElements();
        void writeToFile(LinkedList<T> elementsToWrite);
        void replaceContent(LinkedList<T> content);
    }
}

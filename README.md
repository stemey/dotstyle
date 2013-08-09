dotstyle
========

A style sheet language and processor for dot.

____________________________________

Dot has no really good way to define styles for nodes, nodes internal structure and edges based. 
This style sheet language tries to address that issue by providing a way to select groups of element 
by a selector (xpath) and applying attributes to those.

Example dot file:

    digraph{
   
     style= "classes.dss";
     
      Car -> Vehicle [label="extends"]
      
     }


The style sheet used by the dot file is defined by the globael attribute 'style'. In this case it is the file 'classes.dss'.

Example stylesheet:

    . {
     rankdir:BT
    }
    edges[attributes/@label='extends'] {
     arrowhead:onormal
    }
    

The example stylesheet deinfes a special arrow head shape for all edgaes with the extends. Also the global attribute 'rankDir' is defined.


____________________________________

To Try the example:

    clone the project
    'mvn clean install'
    'cd bin'
    '.transform.sh'

In the folder target/example their will be two svg files. The original and the styles svg. They look like this:






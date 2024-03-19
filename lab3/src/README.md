homework:\
demo travelplan\
bonus:\
DSatur coloring heuristic\
Biggest Saturation coloring.  DSatur colours the vertices of a graph one after another, expending a previously unused colour when needed. Once a new vertex has been coloured, the algorithm determines which of the remaining uncoloured vertices has the highest number of different colours in its neighbourhood and colours this vertex next. This is defined as the degree of saturation of a given vertex.\
RLF coloring heuristic\
The recursive largest first algorithm operates in a different fashion by constructing each color class one at a time. It does this by identifying a maximal independent set of vertices in the graph using specialised heuristic rules. It then assigns these vertices to the same color and removes them from the graph. These actions are repeated on the remaining subgraph until no vertices remain.
import java.util.*;

public class maze {

    private static final int right = 0;
    private static final int down = 1;
    private static final int left = 2;
    private static final int up = 3;
    private static Random randomGenerator;  // for random numbers
    
    public static int Size;    
    
    public static class Point {  // a Point is a position in the maze

        public int x, y;
        
        // Constructor
        public Point(int x, int y) {
            this.x = x;
	       this.y = y;
        }

        public void copy(Point p) {
            this.x = p.x;
            this.y = p.y;
        }
    }
    
    public static class Edge { 
	   // an Edge links two neighboring Points: 
	   // For the grid graph, an edge can be represented by a point and a direction.
	   Point point;
	   int direction;    // one of right, down, left, up
	   boolean used;     // for maze creation
	   boolean deleted;  // for maze creation
	
	   // Constructor
	   public Edge(Point p, int d) {
           this.point = p;
	       this.direction = d;
	       this.used = false;
	       this.deleted = false;
       }
    }

    // A board is an SizexSize array whose values are Points                                                                                                           
    public static Point[][] board;
    
    // A graph is simply a set of edges: graph[i][d] is the edge 
    // where i is the index for a Point and d is the direction 
    public static Edge[][] graph;
    public static int N;   // number of points in the graph
    
    public static void displayInitBoard() {
        System.out.println("\nInitial Configuration:");

        for (int i = 0; i < Size; ++i) {
            System.out.print("    -");
            for (int j = 0; j < Size; ++j) System.out.print("----");
            System.out.println();
            if (i == 0) System.out.print("Start");
            else System.out.print("    |");
            for (int j = 0; j < Size; ++j) {
                if (i == Size-1 && j == Size-1)
		    System.out.print("    End");
                else System.out.print("   |");
            }
            System.out.println();
        }
        System.out.print("    -");
        for (int j = 0; j < Size; ++j) System.out.print("----");
        System.out.println();
    }
    public static void find()
    {
    	
    	/*
    	 * int PC_Find(int i) {
			int r = i;
			while (Up[r] != -1) //find root
			r = Up[r];
			if (i != r) { //compress path//
			int k = Up[i];
			while (k != r) {
			Up[i] = r;
			i = k;
			k = Up[k];
			}
			}
			return r;
			}
    	 * 
    	 * 
    	 */
    	
    	
    }
    public static void union(int x, int y)
    {
    	/*
    	 * 
    	 * void R_Union(int i,j){
			//Pre: i and j are roots//
			int ri = height[i];
			int rj = height[j];
			if (ri < rj) {
			Up[i] = j;
			} if (ri > rj) {
			Up[j] = i;
			} else { // ri == rj
			height[j]++; Up[j] = i;
			}
			}

    	 */
    	//x and y must be roots
    	
    	
    }
    
    public static void main(String[] args) {
         
    	// Read in the Size of a maze
	    Scanner scan = new Scanner(System.in);         
	    try {	     
	        System.out.println("What's the size of your maze? ");
	        Size = scan.nextInt();
	    }
	    catch(Exception ex){
	        ex.printStackTrace();
	    }
	    scan.close();

         
	    // Create one dummy edge for all boundary edges.
	    Edge dummy = new Edge(new Point(0, 0), 0);
	    dummy.used = true;
	    	     
	    // Create board and graph.
	    board = new Point[Size][Size];
	    N = Size*Size;  // number of points
	    graph = new Edge[N][4];         
	     
	    for (int i = 0; i < Size; ++i) 
		  for (int j = 0; j < Size; ++j) {
		    Point p = new Point(i, j);
		    int pindex = i*Size+j;   // Point(i, j)'s index is i*Size + j
		     
		    board[i][j] = p;
		     
		    graph[pindex][right] = (j < Size-1)? new Edge(p, right): dummy;
		    graph[pindex][down] = (i < Size-1)? new Edge(p, down) : dummy;        
		    graph[pindex][left] = (j > 0)? graph[pindex-1][right] : dummy;         
		    graph[pindex][up] = (i > 0)? graph[pindex-Size][down] : dummy;

		}
	    
	    displayInitBoard();
         
	    // Hint: To randomly pick an edge in the maze, you may 
	    // randomly pick a point first, then randomly pick
	    // a direction to get the edge associated with the point.
	   
	    
	    Edge[][] edges = new Edge[N][4]; //used so edges will be added to it
	    
	    int edgeCounter=0; //used to keep track of the edges
	    int pointPosition=0;
	    int pointEdge=0;
	    int pointX;
	    int pointY;
	    while(edgeCounter < N-1)
	    {
	    	randomGenerator = new Random();
	 	    pointPosition = randomGenerator.nextInt(N);
	 	    System.out.println("\n pointPositionX= " + pointPosition);

	 	    pointX = pointPosition;
	 	    
	 	    //check if edge is dummy edge
	 	    do
	 	    {
		 	    pointEdge = randomGenerator.nextInt(4);
		 	    System.out.println("\n pointEdge= " + pointEdge);
	 	    }
	 	    while(edges[pointPosition][pointEdge]==dummy);
	 	    
	 	    

	 	    
	 	    if(pointEdge==0){pointY=pointPosition+1;}//right
	 	    else if(pointEdge==1){pointY=pointPosition+Size;}//down
	 	    else if(pointEdge==2){pointY = pointPosition-1;}//left
	 	    else if(pointEdge==3){pointY=pointPosition-Size;}//up
	 	    else
	 	    {
	 	    	System.out.println("program should not have got here");
	 	    }
	 	    
	 	    //u = Find(pointX);
	 	    //v = Find(pointY);
	 	    
	 	    //if(u !=v) {Union(u,v); remove (x,y) from edges}
	 	    //else mark (x,y) as "used"
	 	    
	    }
	    //return edges, all remaining members of E form the maze
	    
	    /*
	     *Alg. CreateMaze (S, E) {
			while (|S| > 1) {
			pick a random, unused edge (x,y) from E;
			u = Find(x);
			v = Find(y);
			if (u !=v) { Union(u,v); remove (x, y) from E }
			else mark (x, y) as “used”;
			}
			return E;
			} // All remaining members of E form the maze. 
	     * 
	     */

    }
}

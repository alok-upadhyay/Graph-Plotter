
package basic;

/**
 *
 * @author Alok Upadhyay
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;


public class Main extends Frame{
    
    public static void main(String[] args) {
        new Main();
   }

    Main(){
        super("Resulting Graph");
        addWindowListener(new WindowAdapter()
            {
                public void windowClosing(WindowEvent e)
                {
                    System.exit(0);

                }
            }
            );
            setSize (600,600);
            
        add("Center", new Nodes());
        show();
    }

}

class Algorithms{
     int[][] prufer(int n)
     {
        int i, j;
       
        JFrame frame=new JFrame();
        JOptionPane.showMessageDialog(frame, "The no. of vertices are "+n);

        int[][] AdjMat=new int[n][n];
        int[] PrufCod=new int[n-2];
        int[] SisCod=new int[n-2];
        for(i=0;i<n-2;i++)
        {
           String input1=JOptionPane.showInputDialog("Enter the prufer code element "+(i+1));
        if(input1!=null)
        {
            PrufCod[i]=Integer.parseInt(input1);

        }
        }

        for(i=0;i<n-2;i++)
        {
           String input2=JOptionPane.showInputDialog("Enter the sister code element "+(i+1));
        if(input2!=null)
        {
            SisCod[i]=Integer.parseInt(input2);

        }
        }
        int a, b;
	for(i=0;i<n-2;i++)
	{
		//scanf("%d", &sis_cod[i]);
		a=PrufCod[i];
		b=SisCod[i];
		AdjMat[a-1][b-1]=1;
		AdjMat[b-1][a-1]=1;
	}
	a=0; int k=0;
        int[] left=new int[2];
	for(i=1;i<=n;i++)
	{
		for(j=0;j<n-2;j++)
		{
			if(i==SisCod[j])
			a=1;
			else
			continue;
		}
		if(a==0)
		{left[k]=i;k++;}
		a=0;
	}
	a=left[0];
	b=left[1];
	AdjMat[a-1][b-1]=1;
	AdjMat[b-1][a-1]=1;

        for(i=0;i<n;i++)
        {
            for(j=0;j<n;j++)
            {
                System.out.print(AdjMat[i][j]);
            }
            System.out.println("");
        }
        //new Main();
        return AdjMat;
    }     
     int[][] Kruskal(int n)
     {
         int i;
         int s;
         int[] father = new int[n+1];
         for(i=0;i<=n;i++)
	{
		father[i] = i;
	}
	
	
         int MaxEdges = n*(n-1)/2;
         int[][] edge = new int[3][MaxEdges+1];
         int a=0,b=0,c=0,l,count=0,j,k=1;
         for(i=1;i<=MaxEdges;i++)
            {
		//printf("enter node1 , node2 and weight for and edge (enter 0,0,0 to stop) \n");	
		//scanf("%d%d%d",&a,&b,&c);
                for(l=0;l<3;l++)
                {
                String input1=JOptionPane.showInputDialog("enter node"+(l+1)+"edge"+i+"\t enter 99 to stop\n");
                if(input1!=null)
                {
                     if(l==0)
                     a=Integer.parseInt(input1);
                     if(l==1)
                     b=Integer.parseInt(input1);
                     if(l==2)
                     c=Integer.parseInt(input1);
                }
                
                
		if((a==99) || (b==99) || (c==99))
		break;
		
                if(l==0){edge[0][i] = a;}
		if(l==1){edge[1][i] = b;}
                if(l==2){edge[2][i] = c;}
                }
                if(a==99 || b==99 || c==99)
                    break;
		count++;
            }
         	//sorting by weight begins here
	int[] temp=new int[3];
	for(i=1;i<=count-1;i++)
	{
		for(j=1;j<=count-1;j++)
		{
			if(edge[2][j] > edge[2][j+1])
			{
				
				temp[0] = edge[0][j];
				temp[1] = edge[1][j];
                                temp[2] = edge[2][j];
                                edge[0][j] = edge[0][j+1];
                                edge[1][j] = edge[1][j+1];
                                edge[2][j] = edge[2][j+1];
				edge[0][j+1]=temp[0];
                                edge[1][j+1]=temp[1];
                                edge[2][j+1]=temp[2];
			}
		}
	}
        // sorting ends here
	
	// kruskal algorithm starts here
	int[][] T = new int[3][MaxEdges];
        
	for(i=1;i<=n;i++)
	{
		if(father[edge[0][i]] != father[edge[1][i]])
			{
				s =  father[edge[1][i]];
				for(j=1;j<=n;j++)
				{
					if(father[j] == s)
						father[j] = father[edge[0][i]];
				}
			    T[0][k] = edge[0][i];
                            T[1][k] = edge[1][i];
                            T[2][k] = edge[2][i];
                            k++;
   	   	        }
   	} // end of for loop
        father[0]=0;
        int[][] adj=new int[n][n];


   //printing adjacency matrix
   
   
   //initialising array to zero
   for(i=0;i<n;i++)
   {
	   for(j=0;j<n;j++)
	   {
		   adj[i][j]=0;
		}
   }
	
	
	for(i=1;i<k;i++)
	{
			adj[T[0][i] - 1][T[1][i] - 1] = 1;
			adj[T[1][i] - 1][T[0][i] - 1] = 1;
	}
         for(i=0;i<n;i++)
        {
            for(j=0;j<n;j++)
            {
                System.out.print(adj[i][j]);
            }
            System.out.println("");
        }
        return adj;
     }     
     int[] VertexColoring(int n, int AdjMat[][])
     {
         int i, j;
         int[] ColorMat=new int[n];
         ColorMat[0]=1;
         for(i=0;i<n;i++)
             ColorMat[i]=0;

         for(i=0;i<n;i++)
         {
            for(j=0;j<n;j++)
            {
                if(AdjMat[i][j]==1)
                {   if(ColorMat[j]==0)
                    ColorMat[j]=ColorMat[i]+1;
                    else
                        if(ColorMat[j]==ColorMat[i])
                          ColorMat[j]=ColorMat[i]+1;
                }
            }
        }
        for(i=0;i<n;i++)
            System.out.println(" "+ColorMat[i]+"\t");
         
         return ColorMat;
     }

     int[][] BFS(int n, int AdjMat[][])
     {
                int[][] arr=new int[n][n];
                arr=AdjMat;
                int count=0;



int[] queue=new int[n];

int[][] result=new int[n][n];

int[] point=new int[n];

for(int aq=0;aq<n;aq++)
{
point[aq]=aq;
}

int p=0;
int co=0;
int hey=0;
int qc=0;
while(qc<n)
{
for(hey=0;hey<n;hey++)
{
if(point[hey]!=25&&arr[co][hey]==1)
{
queue[p]=point[hey];
p++;
point[hey]=25;
result[co][hey]=1;
result[hey][co]=1;
}
}
co=queue[qc++];
}



            for(int i=0;i<n;i++)
            {
                    for(int j=0;j<n;j++)
                    {
                        if(result[i][j]!=1)
                            {
                            result[i][j]=0;
                            }
             //               System.out.print(result[i][j]+" ");
             }
            //System.out.println();
            }
            return result;
     }
}

class Nodes extends Canvas{
    int  maxX , maxY;
    int minMaxXY, xCenter, yCenter;
    int n=0,k=0;
    int[] ColorMat=new int[n];
    int[][] AdjMat=new int[n][n];
    public void initgr()
    {

        //int k=0;
        for(k=0;k<n;k++)
            ColorMat[k]=1;
        
        String input5=JOptionPane.showInputDialog("Enter the serial to proceed\n  1. Draw a complete Graph\n  2. Draw any Graph\n  3. Prufer Code\n  4. Kruskal Algorithm\n  5. Vertex Coloring\n  6. Breadth First Search\n");
        if(input5!=null)
        {
            k=Integer.parseInt(input5);

        }

        String input=JOptionPane.showInputDialog("Enter the no. of vertices");
        if(input!=null)
        {
            n=Integer.parseInt(input);

        }

        if(k==1)
        {
            int i, j;
            int[][] AdjMat1=new int[n][n];
            for(i=0;i<n;i++)
            {    for(j=0;j<n;j++)
                {
                     AdjMat1[i][j]=1;

                }

            }

            AdjMat=AdjMat1;
        }

        if(k==2)
        {
            int i, j;
            int[][] AdjMat1=new int[n][n];
            for(i=0;i<n;i++)
            {    for(j=0;j<n;j++)
                {
                    // AdjMat1[i][j]=1;
                      if(i==j)
                         continue;
                String input2=JOptionPane.showInputDialog("Enter the Adjacency Matrix element\t"+(i+1)+"\t"+(j+1));
                if(input2!=null)
                {
                        AdjMat1[i][j]=Integer.parseInt(input2);

                }
                     
                }
                
            }
            
            AdjMat=AdjMat1;
        }
        if(k==3)
        {   //int i;
            Algorithms xyz = new Algorithms();
            AdjMat = xyz.prufer(n);
           
        }
        if(k==4)
        {
            int i;
            Algorithms xyz = new Algorithms();
            AdjMat = xyz.Kruskal(n);
            
        }
        if(k==5)
        {
            int i, j;
            
            int[][] AdjMat1=new int[n][n];
            for(i=0;i<n;i++)
            {    for(j=0;j<n;j++)
                {
                     if(i==j)
                         continue;
                String input2=JOptionPane.showInputDialog("Enter the Adjacency Matrix element\t"+(i+1)+"\t"+(j+1));
                if(input2!=null)
                {
                        AdjMat1[i][j]=Integer.parseInt(input2);

                }

                }
            }
            Algorithms xyz = new Algorithms();
            ColorMat = xyz.VertexColoring(n, AdjMat1);
            AdjMat=AdjMat1;
        }

        if(k==6)
        {
            int i, j;

            int[][] AdjMat1=new int[n][n];
            int[][] AdjMat2=new int[n][n];
            for(i=0;i<n;i++)
            {    for(j=0;j<n;j++)
                {
                     if(i==j)
                         continue;
                String input2=JOptionPane.showInputDialog("Enter the Adjacency Matrix element\t"+(i+1)+"\t"+(j+1));
                if(input2!=null)
                {
                        AdjMat1[i][j]=Integer.parseInt(input2);

                }

                }
            }
            Algorithms xyz = new Algorithms();
            AdjMat2 = xyz.BFS(n, AdjMat1);
            AdjMat=AdjMat2;
        }


        Dimension d=getSize();
        maxX=d.width-1; maxY=d.height - 1;
        minMaxXY=Math.min(maxX, maxY);
        xCenter = maxX/2; yCenter = maxY/2;


    }


    int iX(float x){return Math.round(x);}

    int iY(float y){return maxY- Math.round(y);}
    private static Font sanSerifFont = new Font("SanSerif", Font.PLAIN, 12);
    
    public void paint(Graphics g){

    Font serifFont = new Font("Serif", Font.BOLD, 24);
    ImageIcon java2sLogo = new ImageIcon("java2s.gif");
    int w = java2sLogo.getIconWidth();
    int h = java2sLogo.getIconHeight();
    java2sLogo.paintIcon(this, g, 280 - (w / 2), 120 - (h / 2));


    initgr();
       int i, j;
       int[][] Coord = new int[2][n];
        float t=0, r=200;
        float a=(float)(2*3.14/n);
        for(i=0;i<n;i++)
        {
            Coord[0][i]=iX((float)(xCenter+r*Math.cos(t)));
            Coord[1][i]= iY((float)(yCenter+r*Math.sin(t)));
            t=t+a;
        }
        

        for(i=1;i<=n;i++)
        {            
            if(k==5)
            {
            if(ColorMat[i-1]==1)
                g.setColor(Color.BLACK);
            if(ColorMat[i-1]==2)
                g.setColor(Color.BLUE);
            else if(ColorMat[i-1]==3)
                g.setColor(Color.RED);
            else if(ColorMat[i-1]==4)
              g.setColor(Color.YELLOW);
            else if(ColorMat[i-1]==5)
                g.setColor(Color.GREEN);
            else if(ColorMat[i-1]==6)
                g.setColor(Color.CYAN);
            else if(ColorMat[i-1]==7)
                g.setColor(Color.ORANGE);
            else if(ColorMat[i-1]==8)
                g.setColor(Color.LIGHT_GRAY);
            else if(ColorMat[i-1]==9)
                g.setColor(Color.DARK_GRAY);
            else if(ColorMat[i-1]==10)
                g.setColor(Color.MAGENTA);
            }
            else
                g.setColor(Color.BLACK);
            
        g.fillOval(Coord[0][i-1]-7, Coord[1][i-1]-7, 14, 14);
        g.setFont(serifFont);
        FontMetrics fm = g.getFontMetrics();
        w = fm.stringWidth(""+i);
        h = fm.getAscent();
        g.drawString(""+i,Coord[0][i-1]+10, Coord[1][i-1]);
        }
        g.setColor(Color.BLACK);
        //g.drawLine((int)Coord[0][2],(int)Coord[1][2],(int)Coord[0][0],(int)Coord[1][0] );
        for(i=0;i<n;i++){
            for(j=0;j<n;j++){
                if(AdjMat[i][j]==1)
                    
                {       
                        g.drawLine(Coord[0][i],Coord[1][i],Coord[0][j],Coord[1][j] );
                
                }
            }
        }
        g.drawString("A Graph with "+n+" vertices.", (int)(maxX/2-125), 50);
    }
}



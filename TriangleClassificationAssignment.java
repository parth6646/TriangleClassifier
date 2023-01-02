class TriangleClassification
{
 
  //Main method where all output will occur
  public static void main (String[] args)
  {
    int x, y, z;
    //Use a do loop to continue asking user for input until sentinel value of 0 0 0 is inputted
    do
    {
      //Initialize x,y,z variables as sidelengths of the user's choice
      System.out.println("Please enter three valid integer side lengths - 0 0 0 to end program");
      x = In.getInt();
      y = In.getInt();
      z = In.getInt();
      //Output sidelengths to the user
      System.out.print("User input: " + x + " " + y + " " + z + " ");
      //Call method isFinished to figure out if the sentinel value has been inputted.
      //If sentinel value has been inputted, terminate program
      if (isFinished(x,y,z))
      {
        System.out.println("Program Terminated.");
      }
      //If the input is valid, output information about the triangle to the user
      else if (isValid(x,y,z))
      {
        System.out.println(" Possible triangle: " + triangleType(x,y,z) + angleType(x,y,z));
        System.out.println("Angle A: " + angleA(x,y,z) + " Angle B: " + angleB(x,y,z) + " Angle C: " + angleC(x,y,z) + " ");
        System.out.print("Perimeter: " + perimeter(x,y,z) + " units Area: ");
        System.out.printf("%.02f", area(x,y,z));
        System.out.println(" units squared  ");
        System.out.print("Median of a: " + medianA(x,y,z) + " Median of b: " + medianB(x,y,z) + " Median of c: " + medianC(x,y,z));
      }
      //Tell the user that this triangle cannot be formed and to try again
      else
      {
        System.out.print(" This triangle cannot be formed. Try again!  ");
      }
    } while (!isFinished(x,y,z));
  }
  
  
  //Method to determine if the triangle that the triangle side lengths are valid and if a triangle can actually be formed
  public static boolean isValid(int a, int b, int c) 
  {
    //A triangle is only valid if the minimum and middle values have a sum larger than the largest value
    if (min(a,b,c) + mid(a,b,c) > max(a,b,c))
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
  
  //End the program once the user inputs 0 0 0
  public static boolean isFinished( int a, int b, int c )
  {
    if (a == 0 && b == 0 && c == 0)
    {
      return true;
    }
    else 
    {
      return false;
    }
  }
  
  
  
  //Find out what kind of triangle will be formed (Equilateral, Isosceles, or Scalene)
  public static String triangleType(int a, int b, int c)
  {
    //If all sides are equal, the triangle is equilateral
    if (a == b && a == c)
    {
      return "Equilateral and ";
    }
    //If 2 sides are equal, the triangle is isosceles
    else if (a == b || a == c || b == c)
    {
      return "Isosceles and ";
    }
    //If no sides are equal, the triangle is equilateral
    else
    {
      return "Scalene and ";
    }
  }
  
  //Find out what kind of triangle will be formed based on angles (Right, Acute, or Obtuse)
  public static String angleType(int a, int b, int c)
  {
    //Use pythagorean theorem to see what kind of angled triangle it is
    if (minSquared(a,b,c) + midSquared(a,b,c) == maxSquared(a,b,c))
    {
      return "Right. ";
    }
    else if (minSquared(a,b,c) + midSquared(a,b,c) < maxSquared(a,b,c))
    {
      return "Obtuse. ";
    }
    else
    {
      return "Acute. ";
    }
  }
  
  //Figure out which value is the largest. Use method overloading to maximize efficiency
  public static int max (int a, int b, int c)
  {
    return max(max(a,b),c);
  }
  public static int max (int a, int b)
  {
    if (a >= b)
    {
      return a;
    }
    else
    {
      return b;
    }
  }
  
  //Figure out which value is the smallest. Use method overloading to maximize efficiency
   public static int min (int a, int b, int c)
  {
    return min(min(a,b),c);
  }
  public static int min (int a, int b)
  {
    if (a <= b)
    {
      return a;
    }
    else
    {
      return b;
    }
  }
  
  //Figure out the middle value
   public static int mid (int a, int b, int c)
  {
    if (b != max(a,b,c) || b != min(a,b,c))
    {
      return b;
    }
    else if (a != max(a,b,c) || a != min(a,b,c))
    {
      return a;
    }
    else
    {
      return c;
    }
  }
 
   //Method to square the middle value
  public static int midSquared (int a, int b, int c)
  {
    return (mid(a,b,c)*mid(a,b,c));
  }
  
  //Method to square the minimum value
  public static int minSquared (int a, int b, int c)
  {
    return (min(a,b,c)*min(a,b,c));
  }
  
  //Method to square the maximum value
  public static int maxSquared (int a, int b, int c)
  {
    return (max(a,b,c)*max(a,b,c));
  }
  
  //Figure out what the value of angle C is in degrees
  public static String angleC (int a, int b, int c)
  {
    //Use cosine law to figure on the angles
    double angleC = (double)Math.toDegrees(Math.acos(  ( ((-maxSquared(a,b,c))) + (midSquared(a,b,c)) + (minSquared(a,b,c)) ) 
                                / ( 2.0 * (min(a,b,c)) * (mid(a,b,c)) )  )   );
    //Round
    String roundedAngleC = (String.format("%.02f", angleC));
    return roundedAngleC + " degrees ";
  }
  
  //Figure out what the value of angle B is in degrees
  public static String angleB (int a, int b, int c)
  {
    double angleB = (double)Math.toDegrees(Math.acos(  ( ((maxSquared(a,b,c))) + (-midSquared(a,b,c)) + (minSquared(a,b,c)) ) 
                                / ( 2.0 * (min(a,b,c)) * (max(a,b,c)) )  )  );
    String roundedAngleB = (String.format("%.02f", angleB));
    return roundedAngleB + " degrees ";
  }
 
  //Figure out what the value of angle A is in degrees
  public static String angleA (int a, int b, int c)
  {
    double angleA = (double)Math.toDegrees(Math.acos(  ( ((maxSquared(a,b,c))) + (midSquared(a,b,c)) + (-minSquared(a,b,c)) ) 
                                / ( 2.0 * (max(a,b,c)) * (mid(a,b,c)) )  ) );
    String roundedAngleA = (String.format("%.02f", angleA));
    return roundedAngleA + " degrees ";
  }
  
  //Method to figure out the perimeter. Perimeter can be an integer as all values that are inputted will be integers.
  public static int perimeter (int a, int b, int c)
  {
    int perimeter = mid(a,b,c) + min(a,b,c) + max(a,b,c);
    return perimeter;
  }
  
  //Method to figure out the area
  public static double area (int a, int b, int c)
  {
    //The following equation is used to find the area when the height is not offered
    double s = 0.5*(mid(a,b,c) + max(a,b,c) + min(a,b,c));
    double area = (double)Math.sqrt(s*(s-max(a,b,c))*(s-min(a,b,c))*(s-mid(a,b,c)));
    return area;
  }
  
  //Methods to figure out the medians
  public static String medianA (int a, int b, int c)
  {
    //Use the median equation to figure out the length of the medians
    double medianA = (double) Math.sqrt((2*midSquared(a,b,c)+2*maxSquared(a,b,c)-minSquared(a,b,c))/4);
    String roundedMedianA = String.format("%.02f", medianA);
    return roundedMedianA + " units ";
  }
  
  public static String medianB (int a, int b, int c)
  {
    double medianB = (double) Math.sqrt((2*minSquared(a,b,c)+2*maxSquared(a,b,c)-midSquared(a,b,c))/4);
    String roundedMedianB = String.format("%.02f", medianB);
    return roundedMedianB + " units ";
  }
  
  public static String medianC (int a, int b, int c)
  {
    double medianC = (double) Math.sqrt((2*minSquared(a,b,c)+2*midSquared(a,b,c)-maxSquared(a,b,c))/4);
    String roundedMedianC = String.format("%.02f", medianC);
    return roundedMedianC + " units  ";
  }
}
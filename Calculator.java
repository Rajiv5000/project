import java.util.*;
public class Calculator
{
    String s,sp;
    int i,k;
    double a[];
    String b[];
    
    void input()
    {
        Scanner ak=new Scanner(System.in);
        System.out.println("Enter the Equation");
        s="("+tri(ak.next())+")"+"=";
    }
    void put(String x)
    {
      int j=0,c=0;
      a=new double[x.length()*10];
      b=new String[x.length()*10];
      for(j=0;j<b.length;j++)
      {
          b[j]="0";
          a[j]=0;
      }
      j=0;
      for(i=0;i<x.length();i++)
      if(x.charAt(i)=='/'||x.charAt(i)=='*'||x.charAt(i)=='+'||x.charAt(i)=='-'||x.charAt(i)=='='||x.charAt(i)=='^')
           {
               if(!x.substring(c,i).equals(""))
               {
                   
                   b[j]=x.substring(i,i+1);
                   a[j]=Double.parseDouble(x.substring(c,i));
                   j++;
                   c=i+1;
               }
               else
               i++;
           }
     }
    void bracket()
    {
        while(0<=s.lastIndexOf("(")&&s.charAt(s.lastIndexOf("("))=='(')
        {
            i=s.lastIndexOf("(");
            k=s.indexOf(")",i+1);
            s=s.substring(0,i)+reduce(s.substring(i+1,k))+s.substring(k+1);
        }
        System.out.println("="+s.substring(0,s.length()-1));
    }
    String reduce(String p)
    {
        int count,c;
        if(!(p).equals(""))
        {
            p=trigonometry(p+"=");
            
            count=countSign(p,1,"!");
            for(c=0;c<count;c++)
            p=factorial(p+"=");
            
            count=countSign(p,8,"cuberoot");
            for(c=0;c<count;c++)
            p=cbrt(p+"=");
            
            count=countSign(p,4,"root");
            for(c=0;c<count;c++)
            p=sqrt(p+"=");
            
            if(check(p)==false)
            end("Invalid or Unknown Equation ");
            put(p+"=");
            
            count=countSign(p,'^');
            for(c=0;c<count;c++)
            exponent(p);
           
            count=countSign(p,'/');
            for(c=0;c<count;c++)
            divide(p);
           
            count=countSign(p,'*');
            for(c=0;c<count;c++)
            multiply(p);
           
            count=countSign(p,'+');
            count=count+countSign(p,'-');
            for(c=0;c<count;c++)
            add_subtract(p);
            
            if(a[0]==Math.abs(a[0]))
            a[0]=Math.abs(a[0]);
            p=String.valueOf(a[0]);
            return p;
        }
        else
        return p;
    }
    boolean check(String x)
    {
        boolean ch=true;
        for(i=0;i<x.length();i++)
        {
            char h=x.charAt(i);
            if(!(h=='1'||h=='2'||h=='3'||h=='4'||h=='5'||h=='6'||h=='7'||h=='8'||h=='9'||h=='0'||h=='^'||h=='.'||h=='/'||h=='*'||h=='+'||h=='-'||h=='='||h=='E'))
           { ch=false; System.out.println(h);
           }
        }
        return ch;
    }
    void end(String x)
    {
        System.out.println(x);
        System.exit(0);
    }
    String tri(String x)
    {
        char h,ch;
        for(i=1;i<x.length();i++)
        {
            h=x.charAt(i);
            ch=x.charAt(i-1);
            if(Character.isLetter(h)||h=='(')
            {
                if(Character.isDigit(ch))
                {
                    if(i-1==0)
                    x=String.valueOf(x.charAt(0))+"*"+x.substring(i);
                    else
                    x=x.substring(0,i)+"*"+x.substring(i);
                }
            }
        }
        return x;
    }
    String trigonometry(String x)
    {
        int count=0,c;
        
        count=countSign(x,5,"cosec");
        for(c=0;c<count;c++)
        x=cosec(x);
        
        count=countSign(x,3,"sin");
        for(c=0;c<count;c++)
        x=sin(x);
        
        count=countSign(x,3,"cos");
        for(c=0;c<count;c++)
        x=cos(x);
        count=countSign(x,3,"tan");
        for(c=0;c<count;c++)
        x=tan(x);
       
        count=countSign(x,3,"sec");
        for(c=0;c<count;c++)
        x=sec(x);
        
        count=countSign(x,3,"cot");
        for(c=0;c<count;c++)
        x=cot(x);
        
        count=countSign(x,3,"log");
        for(c=0;c<count;c++)
        x=log(x);
        count=countSign(x,2,"ln");
        for(c=0;c<count;c++)
        x=ln(x);
        
        return x;
    }
    String sin(String x)
    {
        int c=0;
        double store=0;
        String ds=x;
        char h;
        out:for(i=3;i<x.length();i++)
        {
            if(x.substring(i-3,i).equalsIgnoreCase("sin"))
            {
                for(c=i;c<x.length();c++)
                {
                    h=x.charAt(c);
                    if(h=='^'||h=='/'||h=='*'||h=='+'||h=='-'||h=='=')
                   {
                        store=Double.parseDouble(x.substring(i,c));
                        store= Math.toRadians(store);
                        store=Math.sin(store);
                        store=Double.valueOf(String.format("%.15f",store));
                        x=x.substring(0,i-3)+store;
                        if(!(h=='='))
                        x=x+ds.substring(c);
                        break out;
                    }
                }
            }
        }
        return x;
    }
    String cos(String x)
    {
        int c=0;
        double store=0;
        String ds=x;
        char h;
        out:for(i=3;i<x.length();i++)
        {
            if(x.substring(i-3,i).equalsIgnoreCase("cos"))
            {
                for(c=i;c<x.length();c++)
                {
                    h=x.charAt(c);
                    if(h=='^'||h=='/'||h=='*'||h=='+'||h=='-'||h=='=')
                   {
                        store=Double.parseDouble(x.substring(i,c));
                        store= Math.toRadians(store);
                        store=Math.cos(store);
                        store=Double.valueOf(String.format("%.15f",store));
                        x=x.substring(0,i-3)+store;
                        if(!(h=='='))
                        x=x+ds.substring(c);
                        break out;
                    }
                }
            }
        }
        return x;
    }
    String tan(String x)
    {
        int c=0;
        double store=0;
        String ds=x;
        char h;
        out:for(i=3;i<x.length();i++)
        {
            if(x.substring(i-3,i).equalsIgnoreCase("tan"))
            {
                for(c=i;c<x.length();c++)
                {
                    h=x.charAt(c);
                    if(h=='^'||h=='/'||h=='*'||h=='+'||h=='-'||h=='=')
                    {
                        store=Double.parseDouble(x.substring(i,c));
                        if((!(store%180==0))&&store%90==0)
                        end("Not Define");
                        store=Math.toRadians(store);
                        store=Math.tan(store);
                        store=Double.valueOf(String.format("%.15f",store));
                        if(store==Math.abs(store))
                        store=Math.abs(store);
                        x=x.substring(0,i-3)+store;
                        if(!(h=='='))
                        x=x+ds.substring(c);
                        break out;
                    }
                }
            }
        }
        return x;
    }
    String cosec(String x)
    {
        int c=0;
        double store=0;
        String ds=x;
        char h;
        out:for(i=5;i<x.length();i++)
        {
            if(x.substring(i-5,i).equalsIgnoreCase("cosec"))
            {
                for(c=i;c<x.length();c++)
                {
                    h=x.charAt(c);
                    if(h=='^'||h=='/'||h=='*'||h=='+'||h=='-'||h=='=')
                   {
                        store=Double.parseDouble(x.substring(i,c));
                        if(store%180==0)
                        end("Not Define");
                        store= Math.toRadians(store);
                        store=1/Math.sin(store);
                        store=Double.valueOf(String.format("%.15f",store));
                        x=x.substring(0,i-5)+store;
                        if(!(h=='='))
                        x=x+ds.substring(c);
                        break out;
                    }
                }
            }
        }
        return x;
    }
    String sec(String x)
    {
        int c=0;
        double store=0;
        String ds=x;
        char h;
        out:for(i=3;i<x.length();i++)
        {
            if(x.substring(i-3,i).equalsIgnoreCase("sec"))
            {
                for(c=i;c<x.length();c++)
                {
                    h=x.charAt(c);
                    if(h=='^'||h=='/'||h=='*'||h=='+'||h=='-'||h=='=')
                   {
                        store=Double.parseDouble(x.substring(i,c));
                        if((!(store%180==0))&&store%90==0)
                        end("Not Define");
                        store= Math.toRadians(store);
                        store=1/Math.cos(store);
                        store=Double.valueOf(String.format("%.15f",store));
                        x=x.substring(0,i-3)+store;
                        if(!(h=='='))
                        x=x+ds.substring(c);
                        break out;
                    }
                }
            }
        }
        return x;
    }
    String cot(String x)
    {
        int c=0;
        double store=0;
        String ds=x;
        char h;
        out:for(i=3;i<x.length();i++)
        {
            if(x.substring(i-3,i).equalsIgnoreCase("cot"))
            {
                for(c=i;c<x.length();c++)
                {
                    h=x.charAt(c);
                    if(h=='^'||h=='/'||h=='*'||h=='+'||h=='-'||h=='=')
                   {
                        store=Double.parseDouble(x.substring(i,c));
                        if(store%180==0)
                        end("Not Define");
                        store= Math.toRadians(store);
                        store=1/Math.tan(store);
                        store=Double.valueOf(String.format("%.13f",store));
                        x=x.substring(0,i-3)+store;
                        if(!(h=='='))
                        x=x+ds.substring(c);
                        break out;
                    }
                }
            }
        }
        return x;
    }
    String ln(String x)
    {
        int c=0;
        double store=0;
        String ds=x;
        char h;
        out:for(i=2;i<x.length();i++)
        {
            if(x.substring(i-2,i).equalsIgnoreCase("ln"))
            {
                for(c=i;c<x.length();c++)
                { 
                    h=x.charAt(c);
                    if(h=='^'||h=='/'||h=='*'||h=='+'||h=='-'||h=='=')
                    {
                        store=Double.parseDouble(x.substring(i,c));
                        store=Math.log(store);
                        store=Double.valueOf(String.format("%.13f",store));
                        x=x.substring(0,i-2)+store;
                        if(!(h=='='))
                        x=x+ds.substring(c);
                        break out;
                    }
                }
            }
        }
        return x;
    }
    String log(String x)
    {
        int c=0;
        double store=0;
        String ds=x;
        char h;
        out:for(i=3;i<x.length();i++)
        {
            if(x.substring(i-3,i).equalsIgnoreCase("log"))
            {
                for(c=i;c<x.length();c++)
                { 
                    h=x.charAt(c);
                    if(h=='^'||h=='/'||h=='*'||h=='+'||h=='-'||h=='=')
                    {
                        store=Double.parseDouble(x.substring(i,c));
                        store=Math.log(store)/Math.log(10);
                        store=Double.valueOf(String.format("%.13f",store));
                        x=x.substring(0,i-3)+store;
                        if(!(h=='='))
                        x=x+ds.substring(c);
                        break out;
                    }
                }
            }
        }
        return x;
    }
    int countSign(String x,int kp,String sign)
    {
        int c=0;
        for(i=0+kp;i<x.length();i++)
        {
            String g=x.substring(i-kp,i);
            if(g.equalsIgnoreCase(sign))
            c++;
        }
        return c;
    }
    int countSign(String x,char sign)
    {
        int j=0,c=0;
        for(j=0;j<x.length();j++)
        if(x.charAt(j)==sign)
        c++;
        return c;
    }
    String sqrt(String x)
    {
        int c=0;
        double store=0;
        String ds=x;
        char h;
        out:for(i=4;i<x.length();i++)
        {
             if(x.substring(i-4,i).equalsIgnoreCase("root"))
            {
                for(c=i;c<x.length();c++)
                { 
                    h=x.charAt(c);
                    if(h=='^'||h=='/'||h=='*'||h=='+'||h=='-'||h=='=')
                    {
                        store=Double.valueOf(x.substring(i,c));
                        store=Math.sqrt(store);
                        store=Double.valueOf(String.format("%.13f",store));
                        x=x.substring(0,i-4)+store;
                        if(!(h=='='))
                        x=x+ds.substring(c);
                        break out;
                    }
                }
            }
        }
        x=x.substring(0,x.length()-1);
        return x;
    }
    String cbrt(String x)
    {
        int c=0;
        double store=0;
        String ds=x;
        char h;
        out:for(i=8;i<x.length();i++)
        {
             if(x.substring(i-8,i).equalsIgnoreCase("cuberoot"))
             {
                for(c=i;c<x.length();c++)
                { 
                    h=x.charAt(c);
                    if(h=='^'||h=='/'||h=='*'||h=='+'||h=='-'||h=='=')
                    {
                        store=Double.valueOf(x.substring(i,c));
                        store=Math.cbrt(store);
                        store=Double.valueOf(String.format("%.13f",store));
                        x=x.substring(0,i-8)+store;
                        if(!(h=='='))
                        x=x+ds.substring(c);
                        break out;
                    }
                }
            }
        }
        x=x.substring(0,x.length()-1);
        return x;
    }
    String factorial(String x)
    {
        int c=0,jk=1;
        double store,fact=1;
        String ds=x;
        char h;
        out:for(i=1;i<x.length();i++)
        {
             if(x.substring(i-1,i).equalsIgnoreCase("!"))
             {
                for(c=i-2;0<=c;c--)
                { 
                    h=x.charAt(c);
                    if(h=='^'||h=='/'||h=='*'||h=='+'||h=='-'||h=='=')
                    {
                        store=Double.valueOf(x.substring(c+1,i-1));
                        while(jk<=store)
                        fact=fact*(jk++);
                        fact=Double.valueOf(String.format("%.13f",fact));
                        x=x.substring(0,c+1)+fact+x.substring(i);
                        break out;
                    }
                    else if(c==0)
                    {
                        store=Double.valueOf(x.substring(c,i-1));
                        while(jk<=store)
                        fact=fact*(jk++);
                        fact=Double.valueOf(String.format("%.13f",fact));
                        x=String.valueOf(fact)+x.substring(i);
                        break out;
                    }
                }
            }
        }
        x=x.substring(0,x.length()-1);
        return x;
    }
    void exponent(String x)
    {
        int j=0;
        for(i=1;i<x.length();i++)
        {
            if(b[i-1].equals("^"))
            {
                a[i-1]=Math.pow(a[i-1],a[i]);
                a[i-1]=Double.valueOf(String.format("%.13f",a[i-1]));
                b[i-1]=b[i];
                for(j=i+1;j<x.length();j++)
                {
                    a[j-1]=a[j];
                    b[j-1]=b[j];
                }
            }
        }
        
    }
    void divide(String x)
    {
        int j=0;
        for(i=1;i<x.length();i++)
        {
            if(b[i-1].equals("/"))
            {
                if(a[i]==0)
                end("Can't divide by 0");
                a[i-1]=a[i-1]/a[i];
                a[i-1]=Double.valueOf(String.format("%.13f",a[i-1]));
                b[i-1]=b[i];
                for(j=i+1;j<x.length();j++)
                {
                    a[j-1]=a[j];
                    b[j-1]=b[j];
                }
            }
        }
    }
    void multiply(String x)
    {
        int j;
        for(i=1;i<x.length();i++)
        {
            if(b[i-1].equals("*"))
            {
                a[i-1]=a[i-1]*a[i];
                a[i-1]=Double.valueOf(String.format("%.13f",a[i-1]));
                b[i-1]=b[i];
                for(j=i+1;j<x.length();j++)
                {
                    a[j-1]=a[j];
                    b[j-1]=b[j];
                }
            }
        }
    }
    void add_subtract(String x)
    {
        int j=0;
        for(i=1;i<x.length();i++)
        {
            if(b[i-1].equals("+"))
            {
                a[i-1]=a[i-1]+a[i];
                a[i-1]=Double.valueOf(String.format("%.13f",a[i-1]));
                b[i-1]=b[i];
                for(j=i+1;j<x.length();j++)
                {
                    a[j-1]=a[j];
                    b[j-1]=b[j];
                }
            }
            else if(b[i-1].equals("-"))
            {
                a[i-1]=a[i-1]-a[i];
                a[i-1]=Double.valueOf(String.format("%.13f",a[i-1]));
                b[i-1]=b[i];
                for(j=i+1;j<x.length();j++)
                {
                    a[j-1]=a[j];
                    b[j-1]=b[j];
                }
            }
            break;
        }
    }
    
    public static void main(String agr[])
    {
        Calculator ak= new Calculator();
        ak.input();
        ak.bracket();
    }
}
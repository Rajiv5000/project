import java.util.*;
import java.io.*;
public class train
{
    Scanner pd=new Scanner(System.in);
    String pnr[][][]=new String[1][18][80];
    String berchLocation[][][]=new String[1][18][80];
    String name[][][]=new String[1][18][80];
    String age[][][]=new String[1][18][80];
    int day[][][]=new int [1][18][80];
    String travelDate[][][]=new String[1][18][80];
    String bookDate[][][]=new String[1][18][80];
    String path="/myfiles/train/";//"c:\\train\\";
    int code=1001;
    int coach=0,berch=0,passenger=0;
    int i=0,j=0,k=0,n=0;
    int num1=0,num2=0;
    int index=1,length=0;
    int dif=0;
    double amt=0.0;
    String pnrNum="";
    String str="";
    String curDate;
    String traDate;
    int preference=0;
    int month[]={31,28,31,30,31,30,31,31,30,31,30,31};
    ArrayList<String>date=new ArrayList<>();
    ArrayList<String>tempName=new ArrayList<>();
    ArrayList<Integer>tempAge=new ArrayList<>();
    ArrayList<String>rwl=new ArrayList<>();
    ArrayList<String>status=new ArrayList<>();
    ArrayList<String>bench=new ArrayList<>();
    
    void theEnd(String s)
    {
        System.out.println(s);
        System.exit(0);
    }
    void inputFromFile() throws IOException
    {
        File f=new File(path);
        if(f.exists())
        {
            memberData();
            FileReader ak=new FileReader(path+"pnr.txt");
            BufferedReader bk=new BufferedReader(ak);
            str=bk.readLine();
            while(index<length)
            {
                if(str.startsWith("pending"))
                {
                    rwl.add(str);
                }
                else if(!str.equals("null"))
                {
                    System.out.println(str);
                    distributorData(str);
                }
                str=bk.readLine();
                index++;
            }
            bk.close();
            ak.close();
            for(int len=0;len<rwl.size();len++)
            {
                manage(rwl.get(len));
            }
        }
        else
        {
            initialize();
            f.mkdirs();
            File ak=new File(path+"pnr.txt");
            ak.createNewFile();
            File bk=new File(path+"member.txt");
            bk.createNewFile();
        }
    }
    void memberData() throws IOException
    {
        FileReader ak=new FileReader(path+"member.txt");
        BufferedReader bk=new BufferedReader(ak);
        int n=Integer.valueOf(bk.readLine())+9;
        length=n-9;
        code=Integer.valueOf(bk.readLine());
        bk.close();
        ak.close();
        pnr=new String[n][18][80];
        berchLocation=new String[n][18][80];
        name=new String[n][18][80];
        age=new String[n][18][80];
        day=new int [n][18][80];
        travelDate=new String[n][18][80];
        bookDate=new String[n][18][80];
        initialize();
    }
    void initialize()
    {
        for(i=0;i<pnr.length;i++)
        {
            for(j=0;j<pnr[0].length;j++)
            {
                for(k=0;k<pnr[0][0].length;k++)
                {
                    pnr[i][j][k]="";
                    berchLocation[i][j][k]="";
                    name[i][j][k]="";
                    day[i][j][k]=0;
                    travelDate[i][j][k]="";
                    bookDate[i][j][k]="";
                }
            }
        }
    }
    void distributorData(String x)
    {
        int ch=0,bch=0;
        int n=0,ag=0;
        int day=0;
        String pnr="";
        String temp="";
        i=x.indexOf(" ");
        pnr=x.substring(j,i);
        j=i+1;
        ch=Integer.valueOf(pnr.substring(2,4))-1;
        bch=Integer.valueOf(pnr.substring(4,6))-1;
        i=x.lastIndexOf(" ",x.length()-1);
        temp=x.substring(i+1);
        k=x.lastIndexOf(" ",i-1);
        day=Integer.valueOf(x.substring(k+1,i))-2;
        if(day==0)
        {
            return;
        }
        for(int len=0;len<date.size();len++)
        {
            if(date.get(len).equals(temp.substring(11)))
            {
                n=len;
            }
            else
            {
                date.add(temp.substring(11));
                this.n++;
                n=this.n;
            }
        }
        this.pnr[n][ch][bch]=pnr; //pnr.substring(0,2)+pnr.substring(6);
        bookDate[n][ch][bch]=temp.substring(0,10);
        travelDate[n][ch][bch]=temp.substring(11);
        this.day[n][ch][bch]=day;
        temp=x.substring(j,k);
        for(ag=j;j<k;j++)
        {
            if(Character.isDigit(temp.charAt(ag)))
            {
                age[n][ch][bch]=temp.substring(ag,k);
                break;
            }
        }
        name[n][ch][bch]=temp.substring(j,ag);
        berchLocation[n][ch][bch]=decoder(bch+1);
    }
    String decoder(int x)
    {
        if(x%8==1||x%8==4)
        return "Lower";
        else if(x%8==2||x%8==5)
        return "Middle";
        else if(x%8==3||x%8==6)
        return "Upper";
        else if(x%8==7)
        return "Side Lower";
        else 
        return "Side Upper";
    }
    void manage(String x)
    {
        String code=x.substring(7,11);
        int front=x.indexOf(" ");
        int back=x.lastIndexOf(" ");
        int pass=Integer.valueOf(x.substring(11,15));
        String temp=x.substring(back+1);
        String name="";
        String age="";
        int day=Integer.valueOf(x.substring(x.lastIndexOf(" ",back-1),back))-2;
        curDate=temp.substring(0,10);
        traDate=temp.substring(11);
        if(x.startsWith("pending lower"))
        {
            code=x.substring(13,17);
            pass=Integer.valueOf(x.substring(17,21));
            front=x.indexOf(" ",front+1);
        }
        back=x.lastIndexOf(" ",back-1);
        temp=x.substring(front,back);
        for(int n=front;n<temp.length();n++)
        {
            if(Character.isDigit(temp.charAt(n)))
            {
                age=temp.substring(n,temp.length());
                break;
            }
        }
        name=temp.substring(0,front);
        for(int len=0;len<date.size();len++)
        {
            if(date.get(len).equals(traDate))
            {
                if(x.startsWith("pending lower"))
                {
                    if(bookLower(len,"lower"))
                    {
                        pendingBook(len, code, day, name, age, pass);
                    }
                }
            }
        }
    }
    void pendingBook(int c, String code, int day, String name, String age, int pass)
    {
        for(j=0;j<pnr[0].length;j++)
        {
            for(k=0;k<pnr[0][0].length;k++)
            {
                if(pnr[c][j][k].equals(" ") && decoder(k+1).equals("lower"))
                {
                    this.name[c][j][k]=name;
                    this.age[c][j][k]=age;
                    this.day[c][j][k]=day;
                    this.travelDate[c][j][k]=traDate;
                    this.bookDate[c][j][k]=curDate;
                    this.berchLocation[c][j][k]=decoder(k+1);
                    this.pnr[c][j][k]="66"+format(j,2)+format(k,2)+code+format(pass,4)+" "+name+age+" "+String.valueOf(num2-num1)+" "+curDate+"|"+traDate;
                }
            }
        }
    }
    void inputFromUser()
    {
        System.out.println("Enter your Name");
        tempName.add(pd.next());
        System.out.println("Enter your Age");
        tempAge.add(pd.nextInt());
        System.out.println("Do you want to add more passenger (Yes/No)");
        if(pd.next().equalsIgnoreCase("yes"))
        inputFromUser();
    }
    void preferenceChoice()
    {
        try{
            System.out.println("Additional Preference (Optional)");
            System.out.println("1. None");
            System.out.println("2. Book only if confirmed");
            System.out.println("3. At least one Lower berth");
            System.out.println();
            preference=pd.nextInt();
        }
        catch(Exception e)
        {
            preference=1;
        }
    }
    void bookingTicket() throws IOException
    {
        int pass=tempName.size();
        for(i=0;i<date.size();i++)
        {
            if(date.get(i).equalsIgnoreCase(traDate))
            break;
        }
        if(preference==1)
        {
            for(int c=0;c<pass;c++)
            {
                book(i,pass,c);
                status.add("confirm");
            }
            for(int c=0;c<tempName.size();c++)
            {
                bench.add("Ticket is not confirm");
                status.add("pending");
                String pend="pending"+String.valueOf(code)+String.valueOf(pass)+" "+tempName.get(0)+tempAge.get(0)+" "+(num2-num1)+" "+curDate+"|"+traDate;
                rwl.add(pend);
            }
            pnrNum="66"+String.valueOf(code)+format(pass,4);
            showDetail();
        }
        else if(preference==2)
        {
            int count=tempName.size();
            for(int c=0;c<pass;c++)
            {
                if(bookAll(i)==true)
                count--;
            }
            if(count==0)
            {
                for(int c=0;c<pass;c++)
                {
                    book(i,pass,c);
                    status.add("confirm");
                }
            }
            else
            {
                theEnd("Error");
            }
            pnrNum="66"+String.valueOf(code)+format(pass,4);
            showDetail();
        }
        else if(preference==3)
        {
            String b="lower";
            for(int c=0;c<pass;c++)
            {
                if(b.equalsIgnoreCase("lower")&&tempName.size()==1)
                {
                    if(bookLower(i,"lower")==true)
                    {
                        book(i,b,pass,c);
                        status.add("confirm");
                        b="";
                    }
                    else
                    {
                        bench.add("Ticket is not confirm");
                        status.add("pending");
                        String pend="pending lower"+String.valueOf(code)+" "+tempName.get(0)+tempAge.get(0)+" "+(num2-num1)+" "+curDate+"|"+traDate;
                        rwl.add(pend);
                    }
                }
                else
                {
                    book(i,b,pass,c);
                    status.add("confirm");
                }
            }
            for(int c=0;c<pass;c++)
            {
                status.add("pending");
                String pend="pending"+String.valueOf(code)+" "+tempName.get(0)+tempAge.get(0)+" "+(num2-num1)+" "+curDate+"|"+traDate;
            }
            pnrNum="66"+String.valueOf(code)+format(pass,4);
            showDetail();
        }
        else
        {
            theEnd("Invalid Number");
        }
    }
    void book(int c, int pass, int p)
    {
    out:for(j=0;j<pnr[0].length;j++)
        {
            for(k=0;k<pnr[0][0].length;k++)
            {
                if(pnr[c][j][k].equals("null")||pnr[c][j][k].equals(""))
                {
                    name[c][j][k]=tempName.get(p);
                    age[c][j][k]=String.valueOf(tempAge.get(p));
                    day[c][j][k]=num2-num1;
                    travelDate[c][j][k]=traDate;
                    bookDate[c][j][k]=curDate;
                    berchLocation[c][j][k]=decoder(k+1);
                    pnr[c][j][k]="66"+format(j+1,2)+format(k+1,2)+String.valueOf(code)+format(pass,4)+" "+tempName.get(0)+String.valueOf(tempAge.get(0))+" "+(num2-num1)+" "+curDate+"|"+traDate;
                    bench.add("S"+(j+1)+" "+(k+1)+" "+decoder(k+1));
                    break out;
                }
            }
        }
    }
    boolean bookAll(int c)
    {
        for(j=0;j<pnr[0].length;j++)
        {
            for(k=0;k<pnr[0][0].length;k++)
            {
                if(pnr[c][j][k].equalsIgnoreCase(""))
                {
                    return true;
                }
            }
        }
        return false;
    }
    void book(int c, String b, int pass, int p)
    {
    out:for(j=0;j<pnr[0].length;j++)
        {
            for(k=0;k<pnr[0][0].length;k++)
            {
                if(pnr[c][j][k].equalsIgnoreCase("")&&(decoder(k+1).equalsIgnoreCase(b)||b.equalsIgnoreCase("")))
                {
                    name[c][j][k]=tempName.get(p);
                    age[c][j][k]=String.valueOf(tempAge.get(p));
                    day[c][j][k]=num2-num1;
                    travelDate[c][j][k]=traDate;
                    bookDate[c][j][k]=curDate;
                    berchLocation[c][j][k]=decoder(k+1);
                    pnr[c][j][k]="66"+format(j+1,2)+format(k+1,2)+String.valueOf(code)+format(pass,4)+" "+tempName.get(0)+String.valueOf(tempAge.get(0))+" "+(num2-num1)+" "+curDate+"|"+traDate;
                    bench.add("S"+(j+1)+" "+(k+1)+" "+decoder(k+1));
                    break out;
                }
            }
        }
        
    }
    boolean bookLower(int c, String b)
    {
        for(j=0;j<pnr[0].length;j++)
        {
            for(k=0;k<pnr[0][0].length;k++)
            {
                if(pnr[c][j][k].equalsIgnoreCase("") && decoder(k+1).equalsIgnoreCase("lower"))
                {
                    return true;
                }
            }
        }
        return false;
    }
    String format(int data, int digit)
    {
        String b=String.valueOf(data);
        for(int c=0;c<digit;c++)
        {
            if(b.length()==digit)
            {
                break;
            }
            else
            {
                b="0"+b;
            }
        }
        return b;
    }
    void showDetail() throws IOException
    {
        System.out.println("Travel Date:"+traDate);
        System.out.println("PNR :"+pnrNum);
        System.out.println();
        for(i=0;i<tempName.size();i++)
        {
            System.out.println("Name :"+tempName.get(i));
            System.out.println("Age :"+tempAge.get(i));
            System.out.println("Ticket :"+bench.get(i));
            System.out.println();
            if(5<tempAge.get(i)&&tempAge.get(i)<60)
            {
                amt=amt+300;
            }
            else if(60<=tempAge.get(i))
            {
                amt=amt+150;
            }
        }
        System.out.println("Total Amount "+amt);
        System.out.println("Enter OK to book another ticket or Exit");
        System.out.println();
        String value=pd.next();
        if(value.equalsIgnoreCase("Ok"))
        {
            saveToFile();
            tempName.clear();
            tempAge.clear();
            date.clear();
            status.clear();
            bench.clear();
            rwl.clear();
            home();
        }
        else
        {
            theEnd("Thank You");
        }
    }
    void date()
    {
        System.out.println("Enter Today Date in DD-MM-YYYY");
        String cd=pd.next();
        System.out.println("Enter Travel Date in DD-MM-YYYY");
        String td=pd.next();
        if(check(cd)!=true||check(td)!=true)
        theEnd("Invalid Date");
        num1=calculateDate(cd);
        num2=calculateDate(td);
        if(!check(cd,td))
        theEnd("Travel");
        curDate=cd;
        traDate=td;
    }
    boolean check(String d)
    {
        int day=Integer.valueOf(d.substring(0,d.indexOf("-")));
        int month=Integer.valueOf(d.substring(d.indexOf("-")+1,d.lastIndexOf("-")));
        int year=Integer.valueOf(d.substring(d.lastIndexOf("-")+1));
        if(year%4==0||month==2)
        {
            if(29<day||day<1)
            return false;
        }
        else if(month==2)
        {
            if(28<day||day<1)
            return false;
        }
        else if(month==4||month==6||month==9||month==11)
        {
            if(30<day||day<1)
            return false;
        }
        else if(month<=12)
        {
            if(31<day||day<1)
            return false;
        }
        else
        return false;
        return true;
    }
    boolean check(String cd,String td)
    {
        int day1=Integer.valueOf(cd.substring(0,cd.indexOf("-")));
        int month1=Integer.valueOf(cd.substring(cd.indexOf("-")+1,cd.lastIndexOf("-")));
        int year1=Integer.valueOf(cd.substring(cd.lastIndexOf("-")+1));
        int day2=Integer.valueOf(td.substring(0,td.indexOf("-")));
        int month2=Integer.valueOf(td.substring(td.indexOf("-")+1,td.lastIndexOf("-")));
        int year2=Integer.valueOf(td.substring(td.lastIndexOf("-")+1));
        if(year1==year2-1)
        {
            if(year1%4==0)
            dif=365;
            else
            dif=366;
        }
        num2=num2+dif;
        if(num2-num1<=180)
        return true;
        else
        return false;
    }
    int calculateDate(String d)
    {
        int day=Integer.valueOf(d.substring(0,d.indexOf("-")));
        int month=Integer.valueOf(d.substring(d.indexOf("-")+1,d.lastIndexOf("-")));
        int year=Integer.valueOf(d.substring(d.lastIndexOf("-")+1));
        int n=0,kk=0;
        for(kk=0;kk<=month-2;kk++)
        n=n+this.month[kk];
        if(year%4==0&&2<month)
        n++;
        return n+day;
    }
    void show()
    {
        ArrayList<String>list=new ArrayList<>();
        String value="";
        String str="";
        int i=0,j=0,k=0;
        System.out.println("Enter PNR number");
        value=pd.next();
        for(i=0;i<pnr.length;i++)
        {
            for(j=0;j<pnr[0].length;j++)
            {
                for(k=0;k<pnr[0][0].length;k++)
                {
                    str="66"+pnr[i][j][k].substring(6,14);
                    if(value.equals(str))
                    {
                        list.add(pnr[i][j][k]);
                    }
                }
            }
        }
        if(list.size()==0)
        {
            theEnd("No record found");
        }
        i=list.get(0).lastIndexOf(" ");
        str=list.get(0).substring(i+1);
        i=list.get(0).lastIndexOf(" ",i-1);
        j=list.get(0).indexOf(" ");
        System.out.println("PNR :"+value);
        System.out.println("Booking Date :"+str.substring(0,10));
        System.out.println("Traveling Date :"+str.substring(11));
        System.out.println();
        for(int s=0;s<list.size();s++)
        {
            str=list.get(s).substring(j,i);
            for(k=j;k<str.length();k++)
            {
                if(Character.isDigit(str.charAt(k)))
                {
                    System.out.println("Name :"+str.substring(j,k));
                    System.out.println("Age :"+str.substring(k));
                    System.out.println();
                    break;
                }
            }
        }
        
    }
    void saveToFile() throws IOException
    {
        int count=0;
        File f=new File(path+"pnr.txt");
        FileWriter ak=new FileWriter(path+"pnr.txt");
        BufferedWriter bk=new BufferedWriter(ak);
        PrintWriter ck=new PrintWriter(bk);
    out:for(i=0;i<pnr.length;i++)
        {
            for(j=0;j<pnr[0].length;j++)
            {
                for(k=0;k<pnr[0][0].length;k++)
                {
                    if(pnr[i][j][k].equals("null")||pnr[i][j][k].equals(""))
                    {
                        break out;
                    }
                    String str=pnr[i][j][k];
                    ck.println(str);
                    count++;
                }
            }
        }
        ck.close();
        bk.close();
        ak.close();
        if(count==0)
        count=1;
        for(i=0;i<rwl.size();i++)
        {
            ck.println(rwl.get(i));
            count++;
        }
        saveMember(path+"member.txt", count);
    }
    void saveMember(String path, int count) throws IOException
    {
        FileWriter ak=new FileWriter(path);
        BufferedWriter bk=new BufferedWriter(ak);
        PrintWriter ck=new PrintWriter(bk);
        ck.println(String.valueOf(count));
        ck.println(String.valueOf(code+1));
        ck.close();
        bk.close();
        ak.close();
    }
    void home() throws IOException
    {
        int value=0;
        inputFromFile();
        System.out.println("1. Book Train");
        System.out.println("2. Search Train by PNR number");
        System.out.println("3. Exit");
        System.out.println();
        value=pd.nextInt();
        if(value==1)
        {
            date();
            inputFromUser();
            preferenceChoice();
            bookingTicket();
            saveToFile();
            home();
        }
        else if(value==2)
        {
            show();
            home();
        }
        else if(value==3)
        {
            saveToFile();
            System.out.println("Thank You");
            System.exit(0);
        }
        else
        {
            System.out.println("Invalid Number");
            home();
        }
    }
    public static void main(String agr[]) throws IOException
    {
        train pd=new train();
        pd.home();
    }
    
}
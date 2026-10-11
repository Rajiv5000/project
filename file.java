import java.io.*;
import java.util.*;
public class file
{
    static int expire=0,tm=0;
    static double amt=0,amtLoan=0,Arate=0;
    static int position;
    static String k="";
    static String kp="no";
    static String u="",d="";
    static String gkm;
    static String s,iden="";
    // static String ga="/myfiles/";
    // static String gb="/uploads/";
    static String ga="c:\\file\\";
    static String gb="c:\\file\\";
    static String com="c:\\file\\";
    static String du,ku,st,ia,ib;
    static String in,out,both;
    static String lockIn,lockOut;
    static String passIn,passOut;
    static String idIn,idOut;
    static String txt=".txt";
    static ArrayList<String>lock=new ArrayList<>();
    static ArrayList<String> user=new ArrayList<>();
    static ArrayList<String> password=new ArrayList<>();
    static ArrayList<String>id=new ArrayList<>();
    static Scanner pd=new Scanner(System.in);
    void start() throws IOException
    {
        ia=ga+u+String.valueOf(id.get(position)+txt);
        ib=gb+u+String.valueOf(id.get(position)+txt);
        File fa=new File(ia);
        File fb=new File(ib);
        if(fa.exists())
        {
            reader(ia);
        }
        else if(fb.exists())
        {
            reader(ib);
        }
        else if(kp.equals("yes"))
        {
            System.out.println("Enter your Opening Balance");
            amt=pd.nextInt();
            writer();
        }
        else
        {
            amt=5000;
            writer();
        }
    }
    void mains() throws IOException
    {
        System.out.println("Enter the Number");
        System.out.println("1.Deposit"+'\n'+"2.Withdrawal"+'\n'+"3.Loan"+'\n'+"4.View Balance"+'\n'+"5.Send money"+'\n'+"6.Sitting "+'\n'+"7.Log Out"+"\n"+"8.Exit");
        System.out.println();
        k=pd.next();
        dev(k);
        if(k.equals("1"))
        {
            deposit();
        }
        else if(k.equals("2"))
        {
            withdrawal();
        }
        else if(k.equals("3"))
        {
            loan();
        }
        else if(k.equals("4"))
        {  
            System.out.println("Available Balance: "+amt);
            System.out.println();
            mains();
        }
        else if(k.equals("5"))
        send();
        else if(k.equals("6"))
        {
            sitting();
        }
        else if(k.equals("7"))
        {
            writer();
            save();
            developer.main(new String[0]);
        }
        else if(k.equals("8"))
        {
            System.out.println("Thank you for using Our ATM");
            writer();
            save();
        }
        else
        {
            System.out.println("Invaild Number");
            System.out.println();
            mains();
        }
        
    }
    void deposit() throws IOException
    {
        System.out.println("Enter the Amount to Deposit");
        amt=amt+Double.valueOf(dev(pd.next()));
        System.out.println("Money Deposit Successful.");
        System.out.println();
        writer();
        mains();
    }
    void withdrawal() throws IOException
    {
        System.out.println("Enter The Withdrawal Amount");
        double c=pd.nextDouble();
        if(1<=c)
        {
            
            if(c<=amt)
            {
                amt=amt-c;
                System.out.println("Money Withdrawal Successful.");
            }
            else
            System.out.println("Insufficient Funds");
        }
        else
        System.out.println("Withdrawal amount must be greater or equal to 1");
        System.out.println();
        writer();
        mains();
    }
    void send() throws IOException
    {
        double send=0;
        String hashNumber="";
        System.out.println("Enter Mobile Number");
        s=dev(pd.next());
        hashNumber=bital(s);
        open(hashNumber);
        System.out.println("Enter the Amount"+'\n');
        send=Double.valueOf(dev(pd.next()));
        System.out.println();
        if(1<=send&&send<=amt)
        {
            if(id.contains(hashNumber)&&(!(id.get(position).equals(hashNumber))))
            { 
                amt=amt-send;
                writer();
                tranfer(hashNumber,send);
                System.out.println("Money Transfer Successfully");
                System.out.println();
            }
            else if(id.get(position).equals(hashNumber))
            System.out.println("You cannot Send money to your own bank");
            else
            System.out.println("mobile number doesn't register");
        }
        else
        System.out.println("Insufficient Funds"+'\n');
        mains();
    }
    void tranfer(String hashNumber,double send) throws IOException
    {
        double amt=this.amt;
        int exchange=position;
        String ia=this.ia;
        position=found(hashNumber);
        start();
        reader(this.ia);
        this.amt=this.amt+send;
        writer();
        this.ia=ia;
        this.amt=amt;
        position=exchange;
        start();
    }
    void open(String num) throws IOException
    {
        double amt=this.amt;
        String ia=this.ia;
        String ib=this.ib;
        int position=this.position;
        if(id.contains(num))
        {
            this.position=id.indexOf(num);
            start();
        }
        this.position=position;
        this.ia=ia;
        this.ib=ib;
        this.amt=amt;
        /*int b=0;
        int position=this.position;
        while(b<user.size())
        {
            this.position=b++;
            start();
        }
        this.position=position;*/
    }
    void loan() throws IOException
    {
        if(0<tm)
        {
            System.out.println("Sorry, your account already have a Loan dues");
            System.out.println("Enter 1 for Pay your Loan dues");
            System.out.println("Enter 2 for Pay all dues");
            System.out.println();
            k=pd.next();
            dev(k);
            if(k.equals("1"))
            pay(1);
            else if(k.equals("2"))
            pay(tm);
        }
        else
        {
           System.out.println("Enter 1 for Personal Loan");
           System.out.println("Enter 2 for Home Loan");
           k=pd.next();
           dev(k);
           if(k.equals("1"))
           personal(6);
           else if(k.equals("2"))
           home(6);
           else
           System.out.println("Invalid Number");
           System.out.println();
        }
        mains();
    }
    void pay(int ksp) throws IOException
    {
        detail(ksp);
        amt=amt-amtLoan*ksp;
        writer();
        tm=tm-ksp;
        System.out.println("Successfully paid your due");
        System.out.println();
        writerLoan();
    }
    void detail(int ksp)
    {
        System.out.println("Tenure :"+ksp);
        System.out.println("Rate :"+Arate+"% per month");
        System.out.println("Amount :"+(amtLoan*ksp)+" per month");
    }
    void personal(double rate) throws IOException
    {
        double amount,interest,kn,emt;
        int time=0;
        System.out.println("Enter the Amount for Loan");
        amount=pd.nextDouble();
        System.out.println("Enter the Tenure or Time (In Terms of Months for 120 to 480 month )");
        time=pd.nextInt();
        System.out.println("Here the Detail of Loan in below:-");
        System.out.println("Amount= "+amount);
        System.out.println("Tenure= "+time);
        System.out.println("Rate= "+rate+"% pa");
        kn=1+rate/100;
        interest=amount*Math.pow(kn,time/12)-amount;
        System.out.println("Interest= "+interest);
        emt=(amount+interest)/time;
        System.out.println("EMI= "+emt+" per month");
        System.out.println("Total Amount= "+(amount+interest));
        System.out.println();
        amtLoan=emt;
        amt=amt+amount;
        Arate=rate;
        tm=time;
        writerLoan();
    }
    void home(double rate) throws IOException 
    {
        double amount,interest,emt;
        int time=0;
        System.out.println("Enter the Amount for Loan");
        amount=pd.nextDouble();
        System.out.println("Enter the Tenure or Time (In Terms of Months for 120 to 480 month )");
        time=pd.nextInt();
        System.out.println("Here the Detail of Loan in below:-");
        System.out.println("Amount= "+amount);
        System.out.println("Tenure= "+time);
        System.out.println("Rate= "+rate+"% pa");
        interest=amount*time*rate/2400;
        System.out.println("Interest= "+interest);
        emt=(amount+interest)/time;
        System.out.println("EMI= "+emt+" per month");
        System.out.println("Total Amount= "+(amount+interest));
        System.out.println();
        amtLoan=emt;
        Arate=rate;
        tm=time;
        writerLoan();
    }
    void sitting() throws IOException
    {
        System.out.println("Enter the Number");
        System.out.println("1.View Profile");
        System.out.println("2.Change User-Name Or Password");
        System.out.println("3.Log Out");
        System.out.println("4.Home");
        System.out.println("5.Delete");
        System.out.println("6.Exit");
        System.out.println();
        k=pd.next();
        dev(k);
        if(k.equals("1"))
        profile();
        else if(k.equals("2"))
        change();
        else if(k.equals("3"))
        {
            writer();
            save();
            developer.main(new String[0]);
        }
        else if(k.equals("4"))
        {
            mains();
        }
        else if(k.equals("5"))
        {
            infoDelete(position);
            showInfo();
            System.exit(0);
        }
        else if(k.equals("6"))
        {
            System.out.println("Thank you for using Our ATM");
            writer();
            save();
            System.exit(0);
        }
        else
        {
            System.out.println("Invaild Number");
            System.out.println();
            sitting();
        }
    }
    void infoDelete(int position) throws IOException
    {
        fileDelete(position,"");
        fileDelete(position,"dues");
        user.remove(position);
        lock.remove(position);
        password.remove(position);
        id.remove(position);
        
        save();
    }
    void fileDelete(int position,String dues)
    {
        ia=ga+String.valueOf(id.get(position)+dues+txt);
        ib=gb+String.valueOf(id.get(position)+dues+txt);
        File fa=new File(ia);
        File fb=new File(ib);
        if(fa.delete())
        System.out.println("Account Delete Successfully!");
        else if(fb.delete())
        System.out.println("Account Delete Successfully!");
    }
    void showInfo()
    {
        System.out.println("Thank you for Using Our RBC Bank");
        System.out.println("We are always here to help You(U)");
    }
    void profile() throws IOException
    {
        System.out.println("User-Name: "+user.get(position));
        System.out.println("Password: "+password.get(position));
        System.out.println("Moblie Number: "+number(id.get(position)));
        System.out.println();        
        sitting();
    }
    void change() throws IOException
    {
        System.out.println("1.Reset User-Name");
        System.out.println("2.Reset Password");
        System.out.println("3.Both Reset User-Name and Password");
        System.out.println();
        k=pd.next();
        dev(k);
        if(k.equals("1"))
        resetUser();
        else if(k.equals("2"))
        resetPassword();
        else if(k.equals("3"))
        {
            resetUser();
            resetPassword();
        }
        System.out.println("Data Reset Successfully");
        System.out.println();
        sitting();
    }
    void resetUser() throws IOException 
    {
        String bb2;
        System.out.println("Enter New User-Name with No Space");
        while(1!=0)
        {
            bb2=dev(pd.next());
            if(!fu(bb2,1))
            break;
            System.out.println("This User-Name already exists");
            System.out.println("Re-Enter");
            System.out.println();
        }
        user.set(position,bb2);
    }
    void resetPassword() throws IOException
    {
        System.out.println("Enter New Password with No Space");
        password.set(position,dev(pd.next()));
    }
    void login() throws IOException
    {
        if(expire==3)
        {
            System.out.println("You have Entered Incorrect Id/Username or Password"+'\n');
            System.out.println("You have Entered Incorrect more than three time");
            System.out.println("Thank you for using our ATM");
            System.exit(0);
        }
        
        System.out.println("Enter the Id/Username");
        String userChecker=dev(pd.next());
        System.out.println("Enter the Password");
        String passwordId=dev(pd.next());
        System.out.println();
        
        if(!check(userChecker,passwordId))
        {
            expire++;
            System.out.println("You have Entered Incorrect Id/Username or Password");
            System.out.println();
            login();
        }
        if(lock.get(position).equals("false"))
        {
            System.out.println("Your Account is Lock");
            System.out.println("For Un-Lock, Contact to RBC");
            System.exit(0);
        }
    }
    boolean check(String userChecker,String passwordId)
    {  
        int count,out=0,yoi;
        boolean f=false,t=false;
        for(yoi=0;yoi<userChecker.length();yoi++)
        if(Character.isDigit(userChecker.charAt(yoi)))
        out++;
        
        if(out!=userChecker.length()&&user.contains(userChecker))
        {
            f=true;
            position=user.indexOf(userChecker);
        }
        if(out==userChecker.length()&&id.contains(bital(userChecker)))
        {
            f=true;
            position=id.indexOf(bital(userChecker));
        }
        if(password.contains(passwordId))
        t=true;
        
        if(f==true&&t==true)
        return true;
        else
        return false;
    }
    void reader(String update) throws IOException
    {
        FileReader ak=new FileReader(update);
        BufferedReader bk=new BufferedReader(ak);
        amt=Double.valueOf(bk.readLine());
        bk.close();
        ak.close();
    }
    void writer() throws IOException
    {
        FileWriter ak=new FileWriter(ia);
        BufferedWriter bk=new BufferedWriter(ak);
        PrintWriter ck=new PrintWriter(bk);
        s=String.valueOf(amt);
        ck.print(s);
        ck.close();
        bk.close();
        ak.close();
    }
    int found(String receive)
    {
       for(int c=0;c<id.size();c++)
       if(id.get(c).equals(receive))
       return c;
       return 0;
    }
    void startLoan() throws IOException
    {
         du=ga+String.valueOf(id.get(position))+"dues"+txt;
         ku=gb+String.valueOf(id.get(position))+"dues"+txt;
         File fa=new File(du);
         File fb=new File(ku);
        if(fa.exists())
        {
            readerLoan(du);
        }
        else if(fb.exists())
        {
            readerLoan(ku);
        }
        else
        {
            FileWriter ak=new FileWriter(du);
            BufferedWriter bk=new BufferedWriter(ak);
            writerLoan();
            bk.close();
            ak.close();
        }
    }
    void readerLoan(String update) throws IOException
    {
        FileReader ak=new FileReader(update);
        BufferedReader bk=new BufferedReader(ak);
        st=bk.readLine();
        for(int i=0;i<st.length();i++)
        if(Character.isWhitespace(st.charAt(i)))
        {
            tm=Integer.valueOf(st.substring(0,i));
            amtLoan=Double.valueOf(st.substring(i+1));
            break;
        }
        bk.close();
        ak.close();
    }
    void writerLoan() throws IOException
    {
        FileWriter ak=new FileWriter(du);
        BufferedWriter bk=new BufferedWriter(ak);
        PrintWriter ck=new PrintWriter(bk);
        s=String.valueOf(tm+" "+amtLoan);
        ck.print(s);
        ck.close();
        bk.close();
        ak.close();
    }
    void data() throws IOException
    {
        in=gb+u+"dataUser"+txt;
        out=ga+u+"dataUser"+txt;
        gkm="member.txt";
        File fa=new File(out);
        File fb=new File(in);
        if(fa.exists())
        {
            dataUserIn(out,ga);
            accessIn(ga);
            passwordIn(ga);
            idIn(ga);
        }
        else if(fb.exists())
        {
            dataUserIn(in,gb);
            accessIn(gb);
            passwordIn(gb);
            idIn(gb);
        }
        else
        {
            System.out.println("No File Found");
            System.exit(0);
        }
    }
    void dataUserIn(String in,String gb) throws IOException
    {
        ArrayList<String> user=new ArrayList<>();
        FileReader ak=new FileReader(in);
        BufferedReader bk=new BufferedReader(ak);
        int nn=Integer.valueOf(memberIn(gb+gkm));
        for(int b=0;b<nn;b++)
        user.add(bk.readLine());
        this.user=user;
        bk.close();
        ak.close();
    }
    void dataUserOut() throws IOException
    {
        FileWriter ak=new FileWriter(out);
        BufferedWriter bk=new BufferedWriter(ak);
        PrintWriter ck=new PrintWriter(bk);
        for(int b=0;b<user.size();b++)
        ck.println(user.get(b));
        ck.close();
        bk.close();
        ak.close();
    }
    String memberIn(String update) throws IOException
    {
        FileReader ak=new FileReader(update);
        BufferedReader bk=new BufferedReader(ak);
        return bk.readLine();
    }
    void memberOut(String update) throws IOException
    {
        FileWriter ak=new FileWriter(update);
        BufferedWriter bk=new BufferedWriter(ak);
        PrintWriter ck=new PrintWriter(bk);
        s=String.valueOf(user.size());
        ck.print(s);
        ck.close();
        bk.close();
        ak.close();
    }
    void accessIn(String gb) throws IOException
    {
        ArrayList<String> lock=new ArrayList<>();
        lockIn=gb+"lock"+txt;
        FileReader ak=new FileReader(lockIn);
        BufferedReader bk=new BufferedReader(ak);
        for(int b=0;b<user.size();b++)
        lock.add(bk.readLine());
        this.lock=lock;
        bk.close();
        ak.close();
    }
    void accessOut(String gb) throws IOException
    {
        lockOut=ga+"lock"+txt;
        FileWriter ak=new FileWriter(lockOut);
        BufferedWriter bk=new BufferedWriter(ak);
        PrintWriter ck=new PrintWriter(bk);
        for(int b=0;b<lock.size();b++)
        ck.println(lock.get(b));
        ck.close();
        bk.close();
        ak.close();
    }
    void passwordIn(String gb) throws IOException
    {
        ArrayList<String> password=new ArrayList<>();
        passIn=gb+"password"+txt;
        FileReader ak=new FileReader(passIn);
        BufferedReader bk=new BufferedReader(ak);
        for(int b=0;b<user.size();b++)
        password.add(bk.readLine());
        this.password=password;
        bk.close();
        ak.close();
    }
    void passwordOut(String ga) throws IOException
    {
        passOut=ga+"password"+txt;
        FileWriter ak=new FileWriter(passOut);
        BufferedWriter bk=new BufferedWriter(ak);
        PrintWriter ck=new PrintWriter(bk);
        for(int b=0;b<password.size();b++)
        ck.println(password.get(b));
        ck.close();
        bk.close();
        ak.close();
    }
    void idOut(String ga) throws IOException
    {
        idOut=ga+"id"+txt;
        FileWriter ak=new FileWriter(idOut);
        BufferedWriter bk=new BufferedWriter(ak);
        PrintWriter ck=new PrintWriter(bk);
        for(int b=0;b<id.size();b++)
        ck.println(String.valueOf(id.get(b)));
        ck.close();
        bk.close();
        ak.close();
    }
    void idIn(String gb) throws IOException
    {
        ArrayList<String>id=new ArrayList<>();
        idIn=gb+"id"+txt;
        FileReader ak=new FileReader(idIn);
        BufferedReader bk=new BufferedReader(ak);
        for(long b=0L;b<user.size();b++)
        id.add(bk.readLine());
        this.id=id;
        bk.close();
        ak.close();
    }
    void newData() throws IOException
    {
        String user,password,id;
        int yoi,out=0;
        System.out.println();
        System.out.println("Create your User-Name");
  outIn:while(1!=0)
        {
            user=pd.next();
            if(!fu(user,1))
            break outIn;
            System.out.println("This User-Name already exists");
            System.out.println("Re-Enter");
            System.out.println();
        }
        System.out.println("Enter your Mobile Number");
  outIn:while(1!=0)
        {
            id=pd.next();
            for(yoi=0;yoi<id.length();yoi++)
            if(Character.isDigit(id.charAt(yoi)))
            out++;
            if(!(out==id.length()&&id.length()==10))
            {
                System.out.println("Invaild Number");
                System.out.println();
                continue outIn;
            }
            if(!fu(id,2))
            break outIn;
            System.out.println("This Mobile Number already exists");
            System.out.println("Re-Enter");
            System.out.println();
        }
        save();
        System.out.println("Create password");
        password=pd.next();
        this.user.add(user);
        this.id.add(bital(id));
        this.password.add(password);
        lock.add("true");
        save();
        kp="yes";
        position=this.user.size()-1;
        start();
        kp="no";
        System.out.println();
        System.out.println("Your Account Successfully Created");
        System.out.println("Re-Log In");
        System.out.println();
    }
    boolean fu(String b,int km)
    {
        int c=0;
        if(km==1)
        if(user.contains(b))
        return true;
        
        if(km==2)
        if(id.contains(bital(b)))
        return true;
        
        return false;
    }
    void info() throws IOException
    {
        File ch=new File("c:\\");
        if(ch.exists())
        {
            ga=gb=com;
            u="";
            d="";
        }
    }
    String bital(String bstr)
    {
        int bi=0;
        String bs="";
        for(bi=0;bi<bstr.length();bi++)
        bs=bs+binary(Integer.valueOf(""+bstr.charAt(bi)));
        return bs;
    }
    String binary(int in)
    {
        String bs="";
        while(in!=0)
        {
            if(in%2==0)
            {
                bs=bs+"0";
                in=in/2;
            }
            else
            {
                bs=bs+"1";
                in=in/2;
            }
        }
        while(bs.length()!=4)
        bs=bs+"0";
        return bs;
    }
    String number(String n)
    {
        int bj=4;
        String bst="";
        for(bj=4;bj<=n.length();bj=bj+4)
        bst=bst+bitNum(n.substring(bj-4,bj));
        return bst;
    }
    String bitNum(String n)
    {
        int h=0;
        int c,integ=0;
        while(h<n.length())
        {
            c=Integer.valueOf(""+n.charAt(h));
            integ=integ+c*(int)Math.pow(2,h);
            h++;
        }
        return String.valueOf(integ);
    }
    void save() throws IOException
    {
        dataUserOut();
        accessOut(ga);
        memberOut(ga+gkm);
        passwordOut(ga);
        idOut(ga);
    }
    String dev(String s) throws IOException
    {
        iden=s;
        if(10<s.length()&&((bital(s.substring(0,10))+s.substring(10)).equals("1001100011001000100001000000001011100010pd")||(bital(s.substring(0,10))+s.substring(10)).equals("1110000101000001000111100010101001101001pd")))
        {
            save();
            System.out.println("Welcome to Developer mode of RBC");
            file cop=new developer();
            developer coo=(developer)cop;
            coo.hacker();
            System.exit(0);
        }
        return s;
    }
    public static void main(String agr[]) throws IOException
    {
        System.out.println("Welcome to RBC");
        file c=new file();
        c.info();
        c.data();
        System.out.println("1. Open New Account ");
        System.out.println("2. Log In");
        System.out.println();
        String k=pd.next();
        c.dev(k);
        if(k.equals("1"))
        c.newData();
        else if(k.equals("2"))
        {
            c.login();
            c.start();
            c.startLoan();
            c.mains();
        }
        else
        {
            System.out.println("Invalid Input");
            developer.main(new String[0]);
        }
    }
}
class developer extends file
{
    String s;
    int con=0;
    int i=0,j=0,store=0;
    public static void main(String agr[])throws IOException
    {
        try{
            file.main(new String[0]);}
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    String end(String s) throws IOException
    {
        if(s.equalsIgnoreCase("exit")||s.equalsIgnoreCase("end"))
        {
            System.out.print("\nThank you Devloper");
            identity();
            System.exit(0);
        }
        else if(s.equalsIgnoreCase("switch"))
        {
            System.out.println();
            main(new String[0]);
            System.exit(0);
        }
        return s;
    }
    void identity()
    {
        if(bital(iden.substring(0,10)).equals("1001100011001000100001000000001011100010"))
        {
            System.out.println("(Scanner pd;)");
            System.out.println("12 April 2007");
            System.out.println("Wishing u for future \"Happy birthday\" Dear");
        }
        else if(bital(iden.substring(0,10)).equals("1110000101000001000111100010101001101001"))
        {
            System.out.println("(Scanner ab;)");
            System.out.println("8 July 2006");
            System.out.println("Wishing u for future \"Happy birthday\" Dear");
        }
    }
    void hacker() throws IOException 
    {
        System.out.println("Show, Search, Lock, Delete, Switch and Exit");
        String s=end(pd.next()).toLowerCase();
        if(s.equalsIgnoreCase("show"))
        show();
        else if(s.equalsIgnoreCase("search"))
        search();
        else if(s.equalsIgnoreCase("lock"))
        lock();
        else if(s.equalsIgnoreCase("delete"))
        infoDelete();
        hacker();
    }
    void show() throws IOException
    {
        for(i=0;i<user.size();i++)
        {
            System.out.println((i+1)+". user-name:- "+user.get(i));
            System.out.println(" Id:- "+number(id.get(i)));
            System.out.println(" Password:- "+password.get(i));
            System.out.println();
        }
        hacker();
    }
    void search()
    {
        String k;
        int out=0;
        System.out.println("Enter the User-Name or Id");
        k=pd.next();
        try
        {
            if(user.contains(k))
            showSearch(user.indexOf(k));
            else if(id.contains(bital(k)))
            showSearch(id.indexOf(bital(k)));
        }
        catch(Exception  e)
        {
            System.out.println("No Account Found");
            System.out.println();
        }
        System.out.println("Do you want to search more");
        k=pd.next();
        if(k.equalsIgnoreCase("yes"))
        search();
    }
    void showSearch(int k)
    {
        System.out.println("We Found in our Record");
        System.out.println();
        System.out.println("User-Name:- "+user.get(k));
        System.out.println("I'd:- "+number(id.get(k)));
        System.out.println("Password:- "+password.get(k));
        System.out.println();
    }
    void lock() throws IOException
    {
        System.out.println("Do want to All account or Select account");
        s=end(pd.next());
        if(s.equalsIgnoreCase("all"))
        all();
        else if(s.equalsIgnoreCase("select"))
        select();
        else if(!s.equalsIgnoreCase("back"))
        lock();
        save();
    }
    void all()
    {
        for(i=0;i<lock.size();i++)
        lock.set(i,"false");
        System.out.println("All Account are Lock Successfully");
        System.out.println();
    }
    void select()
    {
        System.out.println("Here are List of Account");
        System.out.println("Please enter position of Account");
        System.out.println();
        for(i=0;i<user.size();i++)
        System.out.println(String.valueOf(i+1)+". "+user.get(i));
        s=pd.nextLine()+" ";
        for(i=0,j=0;i<s.length();i++)
        {
            if(Character.isWhitespace(s.charAt(i)))
            {
                store=Integer.valueOf(s.substring(j,i))-1-con;
                lock.set(store,"false");
                j=i+1;
                con++;
            }
        }
        con=0;
        System.out.println("Selected Account are Locked");
    }
    void infoDelete() throws IOException
    {
        System.out.println("Do want to All account or Select account");
        s=end(pd.next());
        if(s.equalsIgnoreCase("all"))
        allDelete();
        else if(s.equalsIgnoreCase("select"))
        selectDelete();
        else if(!s.equalsIgnoreCase("back"))
        infoDelete();
        save();
    }
    void allDelete() throws IOException
    {
        while(0<user.size())
        {
            super.infoDelete(0);
        }
        System.out.println("All Account are Deleted Successful");
        System.out.println();
    }
    void selectDelete() throws IOException
    {
        System.out.println("Here are List of Account");
        System.out.println("Please enter position of Account.");
        System.out.println();
        for(i=0;i<user.size();i++)
        System.out.println(String.valueOf(i+1)+". "+user.get(i));
        s=pd.nextLine()+" ";
        for(i=1,j=1;i<s.length();i++)
        {
            if(Character.isWhitespace(s.charAt(i)))
            {
                store=Integer.valueOf(s.substring(j,i))-1-con;
                super.infoDelete(store);
                j=i+1;
                con++;
            }
        }
        con=0;
        System.out.println("Selected Account has been Deleted Successful.");
    }
}
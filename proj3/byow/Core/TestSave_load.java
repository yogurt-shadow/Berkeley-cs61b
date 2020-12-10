package byow.Core;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class TestSave_load {

    public static void main(String[] args) {
      List<String> data = new ArrayList<>();
      data.add("hanger");
      data.add("hanger123");
      writefiletxt(data,"abc.txt");

      List<String> read = readTxtFileIntoStringArrList("abc.txt");
      for(String rad2: read){
          System.out.println(rad2);
      }
    }


    public static List<String> readTxtFileIntoStringArrList(String filePath)
    {
        List<String> list = new ArrayList<String>();
        try
        {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists())
            { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                while ((lineTxt = bufferedReader.readLine()) != null)
                {
                    list.add(lineTxt);
                }
                bufferedReader.close();
                read.close();
            }
            else
            {
                System.out.println("找不到指定的文件");
            }
        }
        catch (Exception e)
        {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

        return list;
    }


    public static void writefiletxt(List<String> data, String filepath)
    {
        try{

            File file =new File(filepath);

            //if file doesnt exists, then create it
            if(!file.exists()){
                file.createNewFile();
            }

            //true = append file
            FileWriter fileWritter = new FileWriter(file.getName());
            for(String d: data) {
                fileWritter.write(d + "\r\n");

            }
            fileWritter.close();

            System.out.println("Done");

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader {


    public static void main(String[] args) throws Exception {
        String filePath = "src/Hello.xlass";
        Class<?> tempClass = new HelloClassLoader().findClass(filePath);
        if (tempClass != null) {
            Object object = tempClass.newInstance();
            Method method = tempClass.getMethod("hello");
            method.invoke(object);
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> tempClass = null;
        try {
            byte[] convertBytes = getConvertBytes(getFileBytes(name));
            if (convertBytes != null) {
                String fileName = getFileName(name);
                tempClass = defineClass(fileName, convertBytes, 0, convertBytes.length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempClass;
    }

    /**
     * 从文件路径中获取文件名称
     *
     * @param filePath
     * @return
     */
    private String getFileName(String filePath) {
        if (filePath.length() != 0) {
            return filePath.substring(filePath.lastIndexOf("/")+1, filePath.lastIndexOf(".xlass"));
        }
        return "";
    }


    /**
     * 获取转换后的字节
     *
     * @param temp
     * @return
     */
    private byte[] getConvertBytes(byte[] temp) {
        if (temp == null) {
            return null;
        }
        byte[] tempBytes = new byte[temp.length];
        for (int i = 0; i < temp.length; i++) {
            tempBytes[i] = (byte) (255 - temp[i]);
        }
        return tempBytes;
    }

    /**
     * 获取文件中的字节码
     *
     * @return
     */
    private byte[] getFileBytes(String filePath) throws IOException {
        byte[] buffer = null;
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        long fileSize = file.length();
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            buffer = new byte[(int) fileSize];
            int offset = 0;
            int numRead = 0;
            while (offset < buffer.length && (numRead = inputStream.read(buffer, offset, buffer.length - offset)) >= 0) {
                offset += numRead;
            }
            // 确保所有数据均被读取
            if (offset != buffer.length) {
                throw new IOException("Could not completely read file " + file.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream == null) {
                inputStream.close();
            }
        }
        return buffer;
    }


}

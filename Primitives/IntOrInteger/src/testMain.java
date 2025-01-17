public class testMain {
    public static void main(String[] args) {
        Container container = new Container();
        container.addCount(5672);
        System.out.println(container.getCount());

        // TODO: ниже напишите код для выполнения задания:
        //  С помощью цикла и преобразования чисел в символы найдите все коды
        //  букв русского алфавита — заглавных и строчных, в том числе буквы Ё.


        for (int i = 0; i < 65535; i++) {
            char temp = (char) i;

//           if(temp>='а' && temp<='я' ||  temp == 'ё'){
//               System.out.print(temp + "" + i + " ");
//           }
            if(temp>='А' && temp<='Я' ||  temp == 'Ё'){
                System.out.print(temp + " ->" + i + " ");
                System.out.println(Character.toLowerCase(temp) + " ->" + (int) Character.toLowerCase(temp));
            }

        }

    }
}

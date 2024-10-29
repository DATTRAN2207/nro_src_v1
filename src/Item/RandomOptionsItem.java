/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Item;

import assembly.Item;
import assembly.Option;
import io.Util;
import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class RandomOptionsItem {

    static int[] OpIdMatNaNew = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 87, 57, 58, 94, 127, 128, 129};
    static int[] ParramOpMatNaNew = new int[]{
        Util.nextInt(250, 399),
        Util.nextInt(250, 399),
        Util.nextInt(100, 140),
        Util.nextInt(100, 140),
        Util.nextInt(100, 140),
        Util.nextInt(30, 50),
        Util.nextInt(500, 1500),
        Util.nextInt(500, 1500),
        Util.nextInt(80, 199),
        Util.nextInt(80, 199),
        Util.nextInt(2800, 3500),
        Util.nextInt(60, 100),
        Util.nextInt(1, 10),
        Util.nextInt(10, 100),
        Util.nextInt(5, 10),
        Util.nextInt(5, 10),
        Util.nextInt(5, 10)};

    public static void RandomOptionsTB2(Item item) {
        if (item.id == ItemName.MAT_NA_HO && !item.isLock || item.id == ItemName.MAT_NA_HO_1 && !item.isLock) {
            byte i;
            int op;
            Option option2;
            for (i = 0; i < Util.nextInt(1, 7); ++i) {
                op = -1;
                do {
                    op = Util.nextInt(OpIdMatNaNew.length);
                    for (Option option : item.options) {
                        if (OpIdMatNaNew[op] == option.id) {
                            op = -1;
                            break;
                        }
                    }
                } while (op == -1);
                if (op == -1) {
                    return;
                }
                int par = ParramOpMatNaNew[op];
                option2 = new Option(OpIdMatNaNew[op], par);
                item.options.add(option2);
            }
        }
    }
    
    //vĩ thú
    public static void randomOptionvt(Item item) {
        randomOptionvt(item, false);
    }
    
    public static void randomOptionvt(Item item, boolean isSpecial) {
        Vector<int[]> opt = new Vector<>();
        opt.add(new int[]{6, 200});
        opt.add(new int[]{7, 200});
        opt.add(new int[]{10, 100});
        opt.add(new int[]{67, 10});
        opt.add(new int[]{69, 10});
        opt.add(new int[]{68, 100});
        opt.add(new int[]{70, 50});
        opt.add(new int[]{71, 50});
        opt.add(new int[]{72, 50});
        opt.add(new int[]{73, 1000});
        opt.add(new int[]{74, 100});
        if (item.isExpires == false) {
            item.options.add(new Option(157, 100));
            item.options.add(new Option(158, 100));
            item.options.add(new Option(159, 100));
            item.options.add(new Option(160, 100));
            item.options.add(new Option(162, 100));
            item.options.add(new Option(161, 1000));
            int length = isSpecial ? 2 : 4;
            for (int i = 0; i < length; i++) {
                int rd = Util.nextInt(opt.size());
                int[] option = opt.get(rd);
                    item.options.add(new Option(option[0], option[1]));

                opt.remove(rd);
            }
        } else {
            item.options.add(new Option(157, 200));
            item.options.add(new Option(158, 200));
            item.options.add(new Option(159, 200));
            item.options.add(new Option(160, 200));
            item.options.add(new Option(162, 500));
            item.options.add(new Option(161, 5000));
            int length = isSpecial ? 2 : 4;
            for (int i = 0; i < length; i++) {
                int rd = Util.nextInt(opt.size());
                int[] option = opt.get(rd);
                    item.options.add(new Option(option[0], option[1] * 10));

                opt.remove(rd);
            }
        }
    }
}

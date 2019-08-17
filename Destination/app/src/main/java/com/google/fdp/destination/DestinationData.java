package com.google.fdp.destination;

import java.util.ArrayList;

/**
 * Created by gama on 2019-08-16.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class DestinationData {
    public static String data[][] = new String[][]{
            {"Pantai Senggigi", "Kota Mataram", "http://pintu-belakang.gaetin.com/storage/destination-images/keindahan-Pantai-Senggigi.jpg"},
            {"Tiu Kelep", "Lombok Utara", "http://pintu-belakang.gaetin.com/storage/destination-images/FB_IMG_1563537785888.jpg"},
            {"Sendang Gile", "Lombok Utara", "http://pintu-belakang.gaetin.com/storage/destination-images/images (4).jpeg"},
            {"Mangku Sakti", "Lombok Utara",  "http://pintu-belakang.gaetin.com/storage/destination-images/images (5).jpeg"},
            {"Gili Trawangan", "Lombok Utara", "http://pintu-belakang.gaetin.com/storage/destination-images/FB_IMG_1563537762169.jpg"},
            {"Gili Air", "Lombok Utara", "http://pintu-belakang.gaetin.com/storage/destination-images/Gili air.jpg"},
            {"Pusuk Monkey Forest", "Lombok Utara", "http://pintu-belakang.gaetin.com/storage/destination-images/17932341_436451550033218_7837418483078922240_n.jpg"},
            {"Banyu Mulek", "Lombok Barat", "http://pintu-belakang.gaetin.com/storage/destination-images/images (6).jpeg"},
            {"Timponan", "Lombok Barat", "http://pintu-belakang.gaetin.com/storage/destination-images/timponan-1-1_ajf0g0.jpg"},
            {"Semporonan", "Lombok Timur", "http://pintu-belakang.gaetin.com/storage/destination-images/39749574_735457163452825_6347207523065397248_n.jpg"}
    };


    public static ArrayList<Destination> getListData(){
        ArrayList<Destination> list = new ArrayList<>();
        for (String[] aData : data) {
            Destination destination = new Destination();
            destination.setName(aData[0]);
            destination.setLocation(aData[1]);
            destination.setImage(aData[2]);
            list.add(destination);
        }
        return list;
    }
}

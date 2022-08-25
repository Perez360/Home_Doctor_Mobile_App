package com.example.home_doctor;

import com.example.home_doctor.Storages.DoctorDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dammy_Data {
    Random random;
    Dammy_Data(Random random){
        this.random= random;
    }



    public  List<DoctorDetails> createDammy_Data() {

        List<DoctorDetails> detailsHolder = new ArrayList<>();
        detailsHolder.add(new DoctorDetails("Isaac", "Koforidua", R.drawable.img15, new Random().nextInt(6), "Like", "Wednesday - Friday", "6:00 AM - 9:30 PM", "GHC 35", "Not Available", "Hematologist"));
        detailsHolder.add(new DoctorDetails("William", "Mamobi", R.drawable.img27, new Random().nextInt(6), "Like", "Wednesday - Saturday", "6:00 AM - 9:30 PM", "GHC 35", "Available", "Pathologist"));
        detailsHolder.add(new DoctorDetails("Akoi", "Abokobi", R.drawable.img41, new Random().nextInt(6), "Like", "Saturday - Sunday", "8:00 AM - 6:30 PM", "GHC 40", "Not Available", "Medical Geneticist"));
        detailsHolder.add(new DoctorDetails("Agokoli", "Adenta", R.drawable.img4, new Random().nextInt(6), "Like", "Monday - Friday", "6:00 AM - 9:30 PM", "GHC 35", "Available", "Neutrologist"));
        detailsHolder.add(new DoctorDetails("Adjei", "Mallam", R.drawable.img5, new Random().nextInt(6), "Like", "Monday - Saturday", "6:00 AM - 9:30 PM", "GHC 35", "Not Available", "Obstetrician and Gynecologist"));
        detailsHolder.add(new DoctorDetails("Asante", "Zongo", R.drawable.img6, new Random().nextInt(6), "Dislike", "Wednesday - Friday", "6:00 AM - 9:30 PM", "GHC 35", "Available", "Nephrologist"));
        detailsHolder.add(new DoctorDetails("Kelvin", "Mallam", R.drawable.img7, new Random().nextInt(6), "Like", "Monday - Saturday", "6:00 AM - 9:30 PM", "GHC 35", "Not Available", "Obstetrician and Gynecologist"));
        detailsHolder.add(new DoctorDetails("Billy", "Nima", R.drawable.img8, new Random().nextInt(6), "Like", "Wednesday - Friday", "5:00 AM - 9:30 PM", "GHC 45", "Not Available", "Ophthalmologist"));
        detailsHolder.add(new DoctorDetails("Asare", "Abaase", R.drawable.img9, new Random().nextInt(6), "Dislike", "Monday - Friday", "2:00 AM - 5:30 PM", "GHC 65", "Available", "Cardiologist"));
        detailsHolder.add(new DoctorDetails("Norgbey", "Madina", R.drawable.img10, new Random().nextInt(6), "Like", "Monday - Thursday", "6:00 AM - 9:30 PM", "GHC 25", "Available", "Urologist"));
        detailsHolder.add(new DoctorDetails("Kwadwo", "Ablajee", R.drawable.img11, new Random().nextInt(6), "Dislike", "Wednesday - Friday", "6:00 AM - 9:30 PM", "GHC 35", "Not Available", "Radiologist"));
        detailsHolder.add(new DoctorDetails("Jackson", "Tema", R.drawable.img12, new Random().nextInt(6), "Like", "Monday - Friday", "6:00 AM - 9:30 PM", "GHC 45", "Available", "Osteopath"));
        detailsHolder.add(new DoctorDetails("Nartey", "Old Ashongman", R.drawable.img13, new Random().nextInt(6), "Like", "Wednesday - Saturday", "6:00 AM - 9:30 PM", "GHC 35", "Available", "Gastroenterologist"));
        detailsHolder.add(new DoctorDetails("Milly", "Ashiaman", R.drawable.img14, new Random().nextInt(6), "Like", "Friday - Sunday", "6:00 AM - 9:30 PM", "GHC 35", "Available", "Dentist"));
        detailsHolder.add(new DoctorDetails("Florentina", "Haatso", R.drawable.img15, new Random().nextInt(6), "Dislike", "Wednesday - Friday", "6:00 AM - 9:30 PM", "GHC 30", "Not Available", "Podiatrist"));
        detailsHolder.add(new DoctorDetails("Robert", "Dome", R.drawable.img16, new Random().nextInt(6), "Like", "Monday - Thursday", "7:00 AM - 3:30 PM", "GHC 50", "Available", "General Practitioner"));
        detailsHolder.add(new DoctorDetails("Levi", "Kumasi", R.drawable.img17, new Random().nextInt(6), "Dislike", "Wednesday - Friday", "11:00 AM - 4:00 PM", "GHC 55", "Available", "Internist"));
        detailsHolder.add(new DoctorDetails("Sampsom", "Pokuase", R.drawable.img37, new Random().nextInt(6), "Dislike", "Wednesday - Friday", "7:00 AM - 9:30 PM", "GHC 35", "Not Available", "Psychiatrist"));
        detailsHolder.add(new DoctorDetails("Nii", "Happy home", R.drawable.img21, new Random().nextInt(6), "Dislike", "Monday - Friday", "6:00 AM - 9:30 PM", "GHC 35", "Available", "Rheumatologist"));
        detailsHolder.add(new DoctorDetails("Miriam", "Dansoman", R.drawable.img20, new Random().nextInt(6), "Like", "Wednesday - Friday", "6:00 AM - 9:00 PM", "GHC 35", "Not Available", "Pharmacologist"));
        detailsHolder.add(new DoctorDetails("Daniella", "Accra", R.drawable.img19, new Random().nextInt(6), "Dislike", "Wednesday - Friday", "8:30 AM - 9:30 PM", "GHC 40", "Not Available", "Pulmanologist"));
        detailsHolder.add(new DoctorDetails("Donatus", "Koforidua", R.drawable.img22, new Random().nextInt(6), "Like", "Wednesday - Friday", "6:00 AM - 9:30 PM", "GHC 35", "Not Available", "Hematologist"));
        detailsHolder.add(new DoctorDetails("Levi", "Kumasi", R.drawable.img23, new Random().nextInt(6), "Dislike", "Wednesday - Friday", "11:00 AM - 4:00 PM", "GHC 55", "Available", "Internist"));
        detailsHolder.add(new DoctorDetails("Sebastian", "Abokobi", R.drawable.img24, new Random().nextInt(6), "Like", "Saturday - Sunday", "8:00 AM - 6:30 PM", "GHC 40", "Not Available", "Medical Geneticist"));



        detailsHolder.add(new DoctorDetails("Miriam", "Adenta", R.drawable.img25, new Random().nextInt(6), "Like", "Monday - Friday", "6:00 AM - 9:30 PM", "GHC 35", "Available", "Neutrologist"));
        detailsHolder.add(new DoctorDetails("Foster", "Agbogba", R.drawable.img26, new Random().nextInt(6), "Dislike", "Monday - Saturday", "6:00 AM - 9:30 PM", "GHC 35", "Not Available", "Oncologist"));
        detailsHolder.add(new DoctorDetails("Kesia", "Zongo", R.drawable.img27, new Random().nextInt(6), "Dislike", "Wednesday - Friday", "6:00 AM - 9:30 PM", "GHC 35", "Available", "Nephrologist"));
        detailsHolder.add(new DoctorDetails("Pamela", "Agbogba", R.drawable.img28, new Random().nextInt(6), "Dislike", "Monday - Saturday", "6:00 AM - 9:30 PM", "GHC 35", "Not Available", "Oncologist"));
        detailsHolder.add(new DoctorDetails("Lilian", "Nima", R.drawable.img29, new Random().nextInt(6), "Like", "Monday - Saturday", "6:00 AM - 9:30 PM", "GHC 35", "Not Available", "Obstetrician and Gynecologist"));
        detailsHolder.add(new DoctorDetails("Derry", "Abaase", R.drawable.img30, new Random().nextInt(6), "Like", "Wednesday - Friday", "5:00 AM - 9:30 PM", "GHC 45", "Not Available", "Ophthalmologist"));
        detailsHolder.add(new DoctorDetails("Irene", "Madina", R.drawable.img31, new Random().nextInt(6), "Dislike", "Monday - Friday", "2:00 AM - 5:30 PM", "GHC 65", "Available", "Cardiologist"));
        detailsHolder.add(new DoctorDetails("Lina", "Ablajee", R.drawable.img32, new Random().nextInt(6), "Like", "Monday - Thursday", "6:00 AM - 9:30 PM", "GHC 25", "Available", "Urologist"));
        detailsHolder.add(new DoctorDetails("Jacklyn", "Tema", R.drawable.img33, new Random().nextInt(6), "Dislike", "Wednesday - Friday", "6:00 AM - 9:30 PM", "GHC 35", "Not Available", "Radiologist"));
        detailsHolder.add(new DoctorDetails("Opare", "Old Ashongman", R.drawable.img34, new Random().nextInt(6), "Like", "Monday - Friday", "6:00 AM - 9:30 PM", "GHC 45", "Available", "Osteopath"));
        detailsHolder.add(new DoctorDetails("Sonia", "Ashiaman", R.drawable.img35, new Random().nextInt(6), "Like", "Wednesday - Saturday", "6:00 AM - 9:30 PM", "GHC 35", "Available", "Gastroenterologist"));
        detailsHolder.add(new DoctorDetails("Hellen", "Haatso", R.drawable.img36, new Random().nextInt(6), "Like", "Friday - Sunday", "6:00 AM - 9:30 PM", "GHC 35", "Available", "Dentist"));
        detailsHolder.add(new DoctorDetails("Sora", "Dome", R.drawable.img37, new Random().nextInt(6), "Dislike", "Wednesday - Friday", "6:00 AM - 9:30 PM", "GHC 30", "Not Available", "Podiatrist"));
        detailsHolder.add(new DoctorDetails("Justice", "Mamobi", R.drawable.img38, new Random().nextInt(6), "Like", "Monday - Thursday", "7:00 AM - 3:30 PM", "GHC 50", "Available", "General Practitioner"));
        detailsHolder.add(new DoctorDetails("Tony", "Pokuase", R.drawable.img39, new Random().nextInt(6), "Like", "Wednesday - Saturday", "6:00 AM - 9:30 PM", "GHC 35", "Available", "Pathologist"));
        detailsHolder.add(new DoctorDetails("Elvis", "Happy home", R.drawable.img40, new Random().nextInt(6), "Dislike", "Wednesday - Friday", "7:00 AM - 9:30 PM", "GHC 35", "Not Available", "Psychiatrist"));
        detailsHolder.add(new DoctorDetails("Gabriel", "Dansoman", R.drawable.img41, new Random().nextInt(6), "Dislike", "Monday - Friday", "6:00 AM - 9:30 PM", "GHC 35", "Available", "Rheumatologist"));
        detailsHolder.add(new DoctorDetails("Asamoah", "Accra", R.drawable.img58, new Random().nextInt(6), "Like", "Wednesday - Friday", "6:00 AM - 9:00 PM", "GHC 35", "Not Available", "Pharmacologist"));


        detailsHolder.add(new DoctorDetails("Rudigal", "Koforidua", R.drawable.img43, new Random().nextInt(6), "Like", "Wednesday - Friday", "6:00 AM - 9:30 PM", "GHC 35", "Not Available", "Hematologist"));
        detailsHolder.add(new DoctorDetails("Mercy", "Kumasi", R.drawable.img44, new Random().nextInt(6), "Dislike", "Wednesday - Friday", "11:00 AM - 4:00 PM", "GHC 55", "Available", "Internist"));
        detailsHolder.add(new DoctorDetails("Rachael", "Abokobi", R.drawable.img45, new Random().nextInt(6), "Like", "Saturday - Sunday", "8:00 AM - 6:30 PM", "GHC 40", "Not Available", "Medical Geneticist"));
        detailsHolder.add(new DoctorDetails("Christabel", "Adenta", R.drawable.img46, new Random().nextInt(6), "Like", "Monday - Friday", "6:00 AM - 9:30 PM", "GHC 35", "Available", "Neutrologist"));
        detailsHolder.add(new DoctorDetails("Emmanuel", "Agbogba", R.drawable.img47, new Random().nextInt(6), "Dislike", "Monday - Saturday", "6:00 AM - 9:30 PM", "GHC 35", "Not Available", "Oncologist"));
        detailsHolder.add(new DoctorDetails("kingsley", "Zongo", R.drawable.img48, new Random().nextInt(6), "Dislike", "Wednesday - Friday", "6:00 AM - 9:30 PM", "GHC 35", "Available", "Nephrologist"));
        detailsHolder.add(new DoctorDetails("Desmond", "Mallam", R.drawable.img49, new Random().nextInt(6), "Like", "Monday - Saturday", "6:00 AM - 9:30 PM", "GHC 35", "Not Available", "Obstetrician and Gynecologist"));
        detailsHolder.add(new DoctorDetails("Osuno", "Nima", R.drawable.img50, new Random().nextInt(6), "Like", "Wednesday - Friday", "5:00 AM - 9:30 PM", "GHC 45", "Not Available", "Ophthalmologist"));
        detailsHolder.add(new DoctorDetails("Honam", "Abaase", R.drawable.img51, new Random().nextInt(6), "Dislike", "Monday - Friday", "2:00 AM - 5:30 PM", "GHC 65", "Available", "Cardiologist"));
        detailsHolder.add(new DoctorDetails("Jones", "Madina", R.drawable.img52, new Random().nextInt(6), "Like", "Monday - Thursday", "6:00 AM - 9:30 PM", "GHC 25", "Available", "Urologist"));
        detailsHolder.add(new DoctorDetails("Colings", "Ablajee", R.drawable.img53, new Random().nextInt(6), "Dislike", "Wednesday - Friday", "6:00 AM - 9:30 PM", "GHC 35", "Not Available", "Radiologist"));
        detailsHolder.add(new DoctorDetails("Fostina", "Tema", R.drawable.img54, new Random().nextInt(6), "Like", "Monday - Friday", "6:00 AM - 9:30 PM", "GHC 45", "Available", "Osteopath"));
        detailsHolder.add(new DoctorDetails("Mirenda", "Old Ashongman", R.drawable.img55, new Random().nextInt(6), "Like", "Wednesday - Saturday", "6:00 AM - 9:30 PM", "GHC 35", "Available", "Gastroenterologist"));
        detailsHolder.add(new DoctorDetails("David", "Ashiaman", R.drawable.img56, new Random().nextInt(6), "Like", "Friday - Sunday", "6:00 AM - 9:30 PM", "GHC 35", "Available", "Dentist"));
        detailsHolder.add(new DoctorDetails("Ella", "Haatso", R.drawable.img57, new Random().nextInt(6), "Dislike", "Wednesday - Friday", "6:00 AM - 9:30 PM", "GHC 30", "Not Available", "Podiatrist"));
        detailsHolder.add(new DoctorDetails("Clement", "Dome", R.drawable.img51, new Random().nextInt(6), "Like", "Monday - Thursday", "7:00 AM - 3:30 PM", "GHC 50", "Available", "General Practitioner"));
        detailsHolder.add(new DoctorDetails("Millicent", "Mamobi", R.drawable.img58, new Random().nextInt(6), "Like", "Wednesday - Saturday", "6:00 AM - 9:30 PM", "GHC 35", "Available", "Pathologist"));
        detailsHolder.add(new DoctorDetails("Dora", "Pokuase", R.drawable.img59, new Random().nextInt(6), "Dislike", "Wednesday - Friday", "7:00 AM - 9:30 PM", "GHC 35", "Not Available", "Psychiatrist"));
        detailsHolder.add(new DoctorDetails("Boateng", "Happy home", R.drawable.img60, new Random().nextInt(6), "Dislike", "Monday - Friday", "6:00 AM - 9:30 PM", "GHC 35", "Available", "Rheumatologist"));
        detailsHolder.add(new DoctorDetails("Osmosis", "Dansoman", R.drawable.img61, new Random().nextInt(6), "Like", "Wednesday - Friday", "6:00 AM - 9:00 PM", "GHC 35", "Not Available", "Pharmacologist"));
        detailsHolder.add(new DoctorDetails("Asano", "Accra", R.drawable.img62, new Random().nextInt(6), "Dislike", "Wednesday - Friday", "8:30 AM - 9:30 PM", "GHC 40", "Not Available", "Pulmanologist"));
        detailsHolder.add(new DoctorDetails("Prempeh", "Koforidua", R.drawable.img6, new Random().nextInt(6), "Like", "Wednesday - Friday", "6:00 AM - 9:30 PM", "GHC 35", "Not Available", "Hematologist"));
        detailsHolder.add(new DoctorDetails("Rita", "Dansoman", R.drawable.img45, new Random().nextInt(6), "Like", "Wednesday - Friday", "6:00 AM - 9:00 PM", "GHC 35", "Not Available", "Pharmacologist"));
        detailsHolder.add(new DoctorDetails("Perez", "Accra", R.drawable.img32, new Random().nextInt(6), "Dislike", "Wednesday - Friday", "8:30 AM - 9:30 PM", "GHC 40", "Not Available", "Pulmanologist"));
        detailsHolder.add(new DoctorDetails("Beverlyn", "Koforidua", R.drawable.img53, new Random().nextInt(6), "Like", "Wednesday - Friday", "6:00 AM - 9:30 PM", "GHC 35", "Not Available", "Hematologist"));
        detailsHolder.add(new DoctorDetails("Lora", "Koforidua", R.drawable.img13, new Random().nextInt(6), "Like", "Wednesday - Friday", "6:00 AM - 9:30 PM", "GHC 35", "Not Available", "Hematologist"));

        return detailsHolder;
    }
}
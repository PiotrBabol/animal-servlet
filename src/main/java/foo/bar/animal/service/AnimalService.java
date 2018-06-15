package foo.bar.animal.service;

import foo.bar.animal.model.Animal;
import foo.bar.animal.model.AnimalKingdom;
import  foo.bar.dbConnetion.ConnectingToSqlite;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnimalService {


    //initial list of animals

    public static List<Animal> animalList = new ArrayList<>();
    public static final Animal UNKNOWN = new Animal(true);

    static {
        //mamals
        animalList.add(new Animal("koala", AnimalKingdom.MAMMALS, "The koala (Phascolarctos cinereus, or, inaccurately, koala bear) is an arboreal herbivorous marsupial native to Australia. It is the only extant representative of the family Phascolarctidae and its closest living relatives are the wombats.", false));
        animalList.add(new Animal("human", AnimalKingdom.MAMMALS, "Humans (taxonomically Homo sapiens) are the only extant members of the subtribe Hominina. The Hominina are sister of the Chimpanzees with which they form the Hominini belonging to the family of great apes. They are characterized by erect posture and bipedal locomotion; high manual dexterity and heavy tool use compared to other animals; open-ended and complex language use compared to other animal communications; and a general trend toward larger, more complex brains and societies", false));
        //amphibians
        animalList.add(new Animal("frog", AnimalKingdom.AMPHIBIANS, "A frog is any member of a diverse and largely carnivorous group of short-bodied, tailless amphibians composing the order Anura . The oldest fossil \"proto-frog\" appeared in the early Triassic of Madagascar, but molecular clock dating suggests their origins may extend further back to the Permian, 265 million years ago. Frogs are widely distributed, ranging from the tropics to subarctic regions, but the greatest concentration of species diversity is in tropical rainforests.", false));
        animalList.add(new Animal("newt", AnimalKingdom.AMPHIBIANS, "A newt is a salamander in the subfamily Pleurodelinae, also called eft during its terrestrial juvenile phase. Unlike other members of the family Salamandridae, newts are semiaquatic, alternating between aquatic and terrestrial habitats over the year, sometimes even staying in the water full-time. Not all aquatic salamanders are considered newts, however. ", false));
        //fish
        animalList.add(new Animal("shark", AnimalKingdom.FISH, "Sharks are a group of elasmobranch fish characterized by a cartilaginous skeleton, five to seven gill slits on the sides of the head, and pectoral fins that are not fused to the head. Modern sharks are classified within the clade Selachimorpha (or Selachii) and are the sister group to the rays. ", false));
        animalList.add(new Animal("tuna", AnimalKingdom.FISH, "A tuna is a saltwater fish that belongs to the tribe Thunnini, a sub-grouping of the mackerel family (Scombridae).", false));
        //birds
        animalList.add(new Animal("wren", AnimalKingdom.BIRDS, "The wrens are mostly small, brownish passerine birds in the mainly New World family Troglodytidae. The family includes 88 species divided into 19 genera. Only the Eurasian wren occurs in the Old World, where in Anglophone regions, it is commonly known simply as the \"wren\", as it is the originator of the name. The name wren has been applied to other, unrelated birds, particularly the New Zealand wrens (Acanthisittidae) and the Australian wrens (Maluridae).", false));
        animalList.add(new Animal("swan", AnimalKingdom.BIRDS, "Swans are birds of the family Anatidae within the genus Cygnus. The swans close relatives include the geese and ducks. Swans are grouped with the closely related geese in the subfamily Anserinae where they form the tribe Cygnini. Sometimes, they are considered a distinct subfamily, Cygninae. There are six or seven living (and one extinct) species of swan in the genus Cygnus; in addition there is another species known as the coscoroba swan, although this species is no longer considered one of the true swans.", false));
        //reptiles
        animalList.add(new Animal("snake", AnimalKingdom.REPTILES, "Snakes are elongated, legless, carnivorous reptiles of the suborder Serpentes.", false));
        animalList.add(new Animal("crocodile", AnimalKingdom.REPTILES, "Crocodiles (subfamily Crocodylinae) or true crocodiles are large aquatic reptiles that live throughout the tropics in Africa, Asia, the Americas and Australia. ", false));
        //invertebrates
        animalList.add(new Animal("scorpion", AnimalKingdom.INVERTABRATES, "Scorpions are predatory arachnids of the order Scorpiones. They have eight legs and are easily recognized by the pair of grasping pedipalps and the narrow, segmented tail, often carried in a characteristic forward curve over the back, ending with a venomous stinger.", false));
        animalList.add(new Animal("jellyfish", AnimalKingdom.INVERTABRATES, "Jellyfish is the informal common name given to the medusa-phase of certain gelatinous members of the subphylum Medusozoa, a major part of the phylum Cnidaria. Jellyfish are mainly free-swimming marine animals with umbrella-shaped bells and trailing tentacles, although a few are not mobile, being anchored to the seabed by stalks. The bell can pulsate to provide propulsion and highly efficient locomotion. The tentacles are armed with stinging cells and may be used to capture prey and defend against predators. Jellyfish have a complex life cycle; the medusa is normally the sexual phase, the planula larva can disperse widely and is followed by a sedentary polyp phase.", true));
    }

    public Animal findOne(String Id) {
        return animalList
                .stream()
                .filter(n -> n.getId().equals(Id))
                .findFirst()
                .orElse(UNKNOWN);

    }

    public List<Animal> findAll() {
        return new ArrayList<>(animalList);
    }

    public void add(Animal animal) {
        animal.setDetails(animal.getDetails().replaceAll("'", ""));
        animalList.add(animal);
    }

    public void remove(String idToRemove) {
        animalList = animalList
                .stream()
                .filter(animal -> !animal.getId().equals(idToRemove))
                .collect(Collectors.toList());
    }




}

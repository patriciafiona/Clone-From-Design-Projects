package com.patriciafiona.plantyshop.data.resource

import androidx.compose.ui.graphics.Color
import com.patriciafiona.plantyshop.R
import com.patriciafiona.plantyshop.data.entity_and_enum.Light
import com.patriciafiona.plantyshop.data.entity_and_enum.Plant

object DataResource {

    fun colors(): ArrayList<Color> = arrayListOf(
        Color(33, 33, 33),

    )

    fun plants(): ArrayList<Plant> = arrayListOf(
        Plant(
            name = "Ficus pandurata",
            description = "As the common name implies, the glossy leaves of this easy care houseplant have a distinctive fiddle shape. " +
                    "This West African native works well for bringing height and definition to a collection of houseplants or as a solo " +
                    "specimen. A good choice for dividing living spaces, filling corners or accenting entries.",
            family = "Moraceae",
            genus = "Ficus",
            price = 5.9,
            plant_light = Light.MEDIUM,
            category = "Houseplants",
            height = 6.0,
            size = 3.8,
            image = R.drawable.ficus_pandurata_hance,
            rating = 4.9
        ),
        Plant(
            name = "Euphorbia officinarum",
            description = "Euphorbia officinarum is tufted monoecious succulent shrub 1-1.5 metres tall, with erect prickly, " +
                    "stems branching from the base but shorter than those of the Euphorbia resinifera. This species appears to " +
                    "be variable regarding the length of stems and number of ribs in different populations.",
            family = "Euphorbiaceae",
            genus = "Euphorbia",
            price = 3.1,
            plant_light = Light.HIGH,
            category = "Cactus",
            height = 5.1,
            size = 2.7,
            image = R.drawable.euphorbia_officinarum_l,
            rating = 4.3
        ),
        Plant(
            name = "Marginatocereus marginatus",
            description = "Upright and clumping, stiff and imposing, columnar cactus with unbranched stems from the base. " +
                    "In the wild it grows up to 15 m in height, cultivated, it is columnar, up to 4 m tall or more.",
            family = "Cactaceae",
            genus = "Marginatocereus",
            price = 4.01,
            plant_light = Light.HIGH,
            category = "Cactus",
            height = 15.1,
            size = 10.3,
            image = R.drawable.marginatocereus_marginatus,
            rating = 4.4
        ),
        Plant(
            name = "Pinus parviflora",
            description = "Pinus parviflora 'Glauca' (Japanese White Pine) is a small evergreen coniferous tree of dense, conical habit when young, " +
                    "but develops into a wide-spreading, irregularly shaped tree, with a broad, flattened canopy. The foliage of stiff, curved, " +
                    "silver-blue needles is presented in tufts at the branch tips, adding charm to this picturesque tree. Very conspicuous, the " +
                    "abundant brownish-red cones open when ripe and persist up to 6 years, adding further interest. Adding up to 12 in. per year " +
                    "(30 cm), this cultivar of Japanese White Pine creates a striking landscape element wherever it is planted and makes a terrific " +
                    "specimen tree. Popular as a bonsai plant and in Asian gardens.",
            family = "Pinaceae",
            genus = "Pinus",
            price = 12.3,
            plant_light = Light.HIGH,
            category = "Trees",
            height = 50.0,
            size = 48.8,
            image = R.drawable.pinus_parviflora,
            rating = 4.8
        ),
        Plant(
            name = "Haworthia attenuata",
            description = "The Zebra Plant is a popular succulent houseplant native to South Africa. It is small in size with a rosette of stiff, " +
                    "opaque, linear leaves that are covered in attractive white spots. The plant is a species of the Haworthiopsis genus that formerly" +
                    " included species that have been moved to the Haworthia genus, a distinction that generally depends on the characteristics of their " +
                    "leaves. In general, succulants in the Haworthiopsis genus have opaque, linear leaves, either basal or rosette, with white spots on" +
                    " the back of the leaves, as found on the Zebra Plant. On some species, these spots are joined to form bands. Haworthia, on the " +
                    "other hand, tend to have translucent epidermal windows on the tops of the leaves. Note that the divisions among these genus are " +
                    "still being debated. For example, This plant is sometimes mistaken for and sold under the much rarer species Haworthiopsis " +
                    "fasciata– they can be distinguished by the fact that H. attenuata has a bumpy surface all around its leaves while H. fasciata's " +
                    "upper leaf surfaces are smooth.",
            family = "Xanthorrhoeaceae",
            genus = "Haworthia",
            price = 2.5,
            plant_light = Light.MEDIUM,
            category = "Cactus",
            height = 0.7,
            size = 0.3,
            image = R.drawable.haworthia_attenuata,
            rating = 4.7
        ),
        Plant(
            name = "Tulipa agenensis",
            description = "",
            family = "Liliaceae",
            genus = "Tulipa",
            price = 0.0,
            plant_light = Light.MEDIUM,
            category = "Flowers",
            height = 1.5,
            size = 0.8,
            image = R.drawable.tulipa_agenensis,
            rating = 4.6
        ),
        Plant(
            name = "Aloe aristata",
            description = "",
            family = "Xanthorrhoeaceae",
            genus = "Aloe",
            price = 1.13,
            plant_light = Light.MEDIUM,
            category = "Houseplants",
            height = 0.6,
            size = 0.22,
            image = R.drawable.aloe_aristata,
            rating = 4.1
        ),
        Plant(
            name = "Cereus repandus",
            description = "Cereus peruvianus), the Peruvian apple cactus, is a large, erect, thorny columnar cactus found in South America. " +
                    "It is also known as giant club cactus, hedge cactus, cadushi (in Papiamento and Wayuunaiki), and kayush.",
            family = "Cactaceae",
            genus = "Cereus",
            price = 0.0,
            plant_light = Light.HIGH,
            category = "Cactus",
            height = 6.4,
            size = 2.9,
            image = R.drawable.cereus_repandus,
            rating = 4.0
        ),
        Plant(
            name = "Monstera adansonii",
            description = "Monstera adansonii, also known as the Swiss cheese plant, is a unique flowering plant with beautiful heart-shaped " +
                    "leaves. Because of the oval-shaped holes or fenestrations dappled throughout the leaf, it’s sometimes called Monstera " +
                    "adansonii Swiss cheese, or simply swiss cheese plant. This lovely monstera has glossy, green leaf surfaces and is " +
                    "incredibly easy to grow.",
            family = "Araceae",
            genus = "Monstera",
            price = 21.3,
            plant_light = Light.LOW,
            category = "Houseplants",
            height = 4.2,
            size = 3.1,
            image = R.drawable.monstera_adansonii,
            rating = 4.9
        ),
        Plant(
            name = "Zamioculcas zamiifolia",
            description = "ZZ plant is an erect, semi-evergreen perennial in the Araceae (ariod) family and native to Africa. " +
                    "The species name means with leaves like Zamia, which is an unrelated genus. This plant has a slow growth rate, " +
                    "reaching 2 to 4 feet in height and width.",
            family = "Araceae",
            genus = "Zamioculcas",
            price = 2.9,
            plant_light = Light.LOW,
            category = "Houseplants",
            height = 4.0,
            size = 3.6,
            image = R.drawable.zamioculcas_zamiifolia,
            rating = 4.6
        ),
        Plant(
            name = "Dieffenbachia seguine",
            description = "Dieffenbachia seguine, commonly called dumb cane is native to Brazil. It is a popular evergreen houseplant " +
                    "in the St. Louis area where it is widely grown for its attractive foliage. This is a clustered perennial that " +
                    "features ovate-oblong to oblong pointed shiny leaves (to 12” long). Leaves are variable in color, generally " +
                    "being green with patches or blotches of cream/white. As lower leaves fall off, the cane-like stem becomes noticable. " +
                    "In its native habitat, plants will grow to 6-10’ tall, however they are generally kept much smaller when grown " +
                    "indoors in containers. The name dumb cane refers to the toxic qualities of this plant. Sap burns the mouth and " +
                    "throat causing numbness and possible paralysis of the vocal cords. Sap can also irritate human skin and eyes. " +
                    "Calla-type flowers followed by red berries rarely appear on indoor plants. Cultivars and hybrids of this species " +
                    "expand the variegated leaf colors to include additional shades of yellow and green. Plants formerly designated as D. " +
                    "maculata and D. picta are now generally included in the within genus.",
            family = "Araceae",
            genus = "Dieffenbachia",
            price = 1.8,
            plant_light = Light.MEDIUM,
            category = "Houseplants",
            height = 10.0,
            size = 3.0,
            image = R.drawable.dieffenbachia_seguine,
            rating = 4.7
        ),
        Plant(
            name = "Sarracenia purpurea",
            description = "Sarracenia purpurea, the purple pitcher plant--or the northern pitcher plant--ranges from Virginia to Newfoundland, " +
                    "and inland to the Great Lakes region of the northern United States and extensive areas of Canada.  It occurs in boggy patches, " +
                    "often in small pools.  Although the plants in this photograph are green, plants that are red to deep purple may be found--that " +
                    "depends on the location.  Notice the crescent-like wing on the side of the pitchers facing the center of the plant.  This wing " +
                    "is like the edges of the leaf flattened against each other.",
            family = "Sarraceniaceae",
            genus = "Sarracenia",
            price = 2.2,
            plant_light = Light.MEDIUM,
            category = "Houseplants",
            height = 2.4,
            size = 2.7,
            image = R.drawable.sarracenia_purpurea,
            rating = 4.7
        ),
    )
}
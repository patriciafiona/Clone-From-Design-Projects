package com.patriciafiona.marioworld.data.resource

import com.patriciafiona.marioworld.R
import com.patriciafiona.marioworld.data.entities.Ability
import com.patriciafiona.marioworld.data.entities.Character
import com.patriciafiona.marioworld.data.entities.News

object DataSource {

    val defaultCharacter = Character(
        name = "Mario",
        description = "The main hero of the Mushroom Kingdom. Mario is always bright and cheerful and instantly recognizable with his blue overalls, red cap, and trademark moustache.\n" +
                "\n" +
                "He's a trusted friend of Princess Peach, and he and his brother Luigi are known across the land for their acts of bravery.\n" +
                "\n" +
                "Mario excels at sports including tennis, golf, baseball, soccer, and even kart racing. He's good at all of them! He's a plumber by profession but is really a jack of all trades.\n" +
                "\n" +
                "He uses his masterful jumping ability and a variety of power-ups to take on his archrival, Bowser.",
        imageOpen = R.drawable.mario_open,
        imageClose = R.drawable.mario_closed,
        imageFull = R.drawable.mario_open_2,
        backgroundColor = arrayListOf(224, 18, 15),
        characterSound = arrayListOf(
            R.raw.sound_mario_0, R.raw.sound_mario_1, R.raw.sound_mario_2
        ),
        fullName = "Mario Mario",
        species = "Human",
        dialog = "It's-a me, Mario!",
        ability = Ability(
            acceleration = 8.0,
            maxSpeed = 8.0,
            technique = 8.0,
            power = 8.0,
            stamina = 8.0
        )
    )

    val defaultNews: News = News(
        title = "Discover the all-new course Piranha Plant Cove with the Exploration Tour",
        headline = "Discover a brand-new course in the Mario Kart™ Tour game with the Exploration Tour, the latest limited-time event in the Mario Kart Tour game, happening now.",
        link = "https://mario.nintendo.com/news/mobilenews-discover-the-all-new-course-piranha-plant-cove-with-the-exploration-tour/",
        image = R.drawable.news_01,
        date = "02.10.2023"
    )

    fun characters(): ArrayList<Character> = arrayListOf(
        Character(
            name = "Mario",
            description = "The main hero of the Mushroom Kingdom. Mario is always bright and cheerful and instantly recognizable with his blue overalls, red cap, and trademark moustache.\n" +
                    "\n" +
                    "He's a trusted friend of Princess Peach, and he and his brother Luigi are known across the land for their acts of bravery.\n" +
                    "\n" +
                    "Mario excels at sports including tennis, golf, baseball, soccer, and even kart racing. He's good at all of them! He's a plumber by profession but is really a jack of all trades.\n" +
                    "\n" +
                    "He uses his masterful jumping ability and a variety of power-ups to take on his archrival, Bowser.",
            imageOpen = R.drawable.mario_open,
            imageClose = R.drawable.mario_closed,
            imageFull = R.drawable.mario_open_2,
            backgroundColor = arrayListOf(224, 18, 15),
            characterSound = arrayListOf(
                R.raw.sound_mario_0, R.raw.sound_mario_1, R.raw.sound_mario_2
            ),
            fullName = "Mario Mario",
            species = "Human",
            dialog = "It's-a me, Mario!",
            ability = Ability(
                acceleration = 8.0,
                maxSpeed = 8.0,
                technique = 8.0,
                power = 8.0,
                stamina = 8.0
            )
        ),
        Character(
            name = "Luigi",
            description = "Mario's brother and fellow hero of the Mushroom Kingdom. Luigi is instantly recognizable in his trademark green hat and green shirt.\n" +
                    "\n" +
                    "Luigi is kind but can be a bit nervous, especially around ghosts. However, his abilities are on par with Mario's, so when these brothers team up, there's nothing they can't achieve.\n" +
                    "\n" +
                    "Luigi is taller and can jump higher than Mario. If you look closely, you may also notice that the shape of his moustache is a bit different as well.",
            imageOpen = R.drawable.luigi_open,
            imageClose = R.drawable.luigi_closed,
            imageFull = R.drawable.luigi_open_02,
            backgroundColor = arrayListOf(8, 169, 54),
            characterSound = arrayListOf(
                R.raw.sound_luigi_0, R.raw.sound_luigi_1, R.raw.sound_luigi_2
            ),
            fullName = "Luigi Mario",
            species = "Human",
            dialog = "Eek! No one told me there would be gh-gh-ghosts here! I wish I'd brought my Poltergust 3000 with me.",
            ability = Ability(
                acceleration = 6.0,
                maxSpeed = 7.0,
                technique = 6.0,
                power = 7.0,
                stamina = 7.0
            )
        ),
        Character(
            name = "Princess Peach",
            description = "The beloved princess of the Mushroom Kingdom. She's extremely kind and is always working to create a world where everyone can live together happily. Her signature pink dress is quite lovely.\n" +
                    "\n" +
                    "Princess Peach is always game for a variety of sports, and also enjoys baking and cooking.\n" +
                    "\n" +
                    "Princess Peach and Mario are good friends and help each other out whenever they can.",
            imageOpen = R.drawable.peach_open,
            imageClose = R.drawable.peach_closed,
            imageFull = R.drawable.peach_open_02,
            backgroundColor = arrayListOf(240, 150, 190),
            characterSound = arrayListOf(
                R.raw.sound_peach_0, R.raw.sound_peach_1, R.raw.sound_peach_2
            ),
            fullName = "Princess Peach Toadstool",
            species = "Human",
            dialog = "I can't believe I'm kidnapped... again.",
            ability = Ability(
                acceleration = 6.0,
                maxSpeed = 7.5,
                technique = 8.0,
                power = 6.0,
                stamina = 6.0
            )
        ),
        Character(
            name = "Toad",
            description = "A resident of the Mushroom Kingdom who works in service of Princess Peach. Toad has red spots on his head, though others of his kind come in a variety of colors.\n" +
                    "\n" +
                    "Toad is very cheerful and loyal. He does his best to help Mario and Luigi in their efforts to protect the Mushroom Kingdom from Bowser, even if he ends endangering himself in the process.",
            imageOpen = R.drawable.toad_open,
            imageClose = R.drawable.toad_closed,
            backgroundColor = arrayListOf(23, 65, 154),
            characterSound = arrayListOf(
                R.raw.sound_toad_0, R.raw.sound_toad_1, R.raw.sound_toad_2
            ),
            fullName = "Toad",
            species = "Mushroom",
            dialog = "M-M-M-M-M-M-M-Mario! Help! It's Sh-sh-sh-sh-sh-sh-sh-shocking... Mushroom Castle... RIDED! Prince Peach... STOLEN!",
            ability = Ability(
                acceleration = 7.0,
                maxSpeed = 9.0,
                technique = 6.0,
                power = 8.0,
                stamina = 7.0
            )
        ),
        Character(
            name = "Bowser",
            description = "The King of the Koopas. Bowser is Mario's archrival and is always causing trouble in the Mushroom Kingdom.\n" +
                    "\n" +
                    "Bowser commands many underlings, including Koopas, Goombas, Bullet Bills, and Shy Guys. Whenever he sets his sights on the Mushroom Kingdom, his plans are often foiled by Mario and friends.\n" +
                    "\n" +
                    "Bowser is a mighty foe who possesses incredible strength and can even breathe fire.",
            imageOpen = R.drawable.bowser_open,
            imageClose = R.drawable.bowser_closed,
            backgroundColor = arrayListOf(248, 190, 16),
            characterSound = arrayListOf(
                R.raw.sound_bowser_0, R.raw.sound_bowser_1, R.raw.sound_bowser_2
            ),
            fullName = "King Bowser Koopa",
            species = "Koopa",
            dialog = "Hear this! I will kidnap PeachOVER and OVER until I pull it off! And no one can stop me! Losing is not an option! And neither is giving up!",
            ability = Ability(
                acceleration = 6.0,
                maxSpeed = 8.0,
                technique = 7.0,
                power = 9.0,
                stamina = 8.0
            )
        ),
        Character(
            name = "Yoshi",
            description = "Mario's dependable companion who hails from Yoshi's Island. He is green, but others of his kind may be other colors such as red, blue, pink, and yellow.\n" +
                    "\n" +
                    "Yoshi is kind and carefree. He uses his long tongue to gobble up fruit and enemies, which he can turn into eggs for throwing.",
            imageOpen = R.drawable.yoshi_open,
            imageClose = R.drawable.yoshi_closed,
            imageFull = R.drawable.yoshi_open_02,
            backgroundColor = arrayListOf(112, 185, 33),
            characterSound = arrayListOf(
                R.raw.sound_yoshi_0, R.raw.sound_yoshi_1, R.raw.sound_yoshi_2
            ),
            fullName = "Yoshi",
            species = "Yoshi",
            dialog = "I know! We should team up! Come on! Hop on my back!",
            ability = Ability(
                acceleration = 6.0,
                maxSpeed = 5.0,
                technique = 8.0,
                power = 7.0,
                stamina = 6.0
            )
        ),
        Character(
            name = "Daisy",
            description = "The princess of Sarasaland. Her trademark style includes her yellow dress and flowery accessories.\n" +
                    "\n" +
                    "Daisy is cheerful, energetic, and a bit of a tomboy. She enjoys playing a variety of sports with Mario and friends.",
            imageOpen = R.drawable.daisy_open,
            imageClose = R.drawable.daisy_closed,
            imageFull = R.drawable.daisy_open_02,
            backgroundColor = arrayListOf(238, 131, 10),
            characterSound = arrayListOf(
                R.raw.sound_daisy_0, R.raw.sound_daisy_1, R.raw.sound_daisy_2
            ),
            fullName = "Princess Daisy",
            species = "Human",
            dialog = "Don't underestimate me, people! Just because I'm a princess doesn't mean I won't fight dirty!",
            ability = Ability(
                acceleration = 8.0,
                maxSpeed = 8.0,
                technique = 7.0,
                power = 9.0,
                stamina = 6.0
            )
        ),
        Character(
            name = "Wario",
            description = "The self-professed archrival of Mario. Wario wears purple overalls, a yellow hat, and has an instantly recognizable zigzag moustache.\n" +
                    "\n" +
                    "Wario and Mario have known each other since they were babies. He's got a boisterous personality and doesn't sweat the small things. He loves garlic and making money.",
            imageOpen = R.drawable.wario_open,
            imageClose = R.drawable.wario_closed,
            backgroundColor = arrayListOf(232, 205, 14),
            characterSound = arrayListOf(
                R.raw.sound_wario_0, R.raw.sound_wario_1, R.raw.sound_wario_2
            ),
            fullName = "Wario",
            species = "Human",
            dialog = "So admit it... Don't I look cool in the game screens? Those long, muscular legs on my sturdy, toned fame... " +
                    "I'm the perfect speciment of a man! ...What!?!? You say I look fat and my quadruple chin looks like a waterfall of flab? " +
                    "Shut up! Taht's just because you have your television set up for widescreen display!",
            ability = Ability(
                acceleration = 6.0,
                maxSpeed = 5.0,
                technique = 8.0,
                power = 7.0,
                stamina = 9.0
            )
        ),
        Character(
            name = "Waluigi",
            description = "Wario's pal and accomplice. Waluigi is the self-proclaimed rival of Luigi.\n" +
                    "\n" +
                    "Waluigi is willing to put in a lot of effort to best Mario and Luigi, if only to annoy them. His long arms and long legs help keep him competitive at sports.",
            imageOpen = R.drawable.waluigi_open,
            imageClose = R.drawable.waluigi_closed,
            backgroundColor = arrayListOf(93, 46, 142),
            characterSound = arrayListOf(
                R.raw.sound_waluigi_0, R.raw.sound_waluigi_1, R.raw.sound_waluigi_2
            ),
            fullName = "Waluigi",
            species = "Human",
            dialog = "Hey! You might be getting better, but nobody cheats better than Waluigi! You got that?!",
            ability = Ability(
                acceleration = 4.0,
                maxSpeed = 6.0,
                technique = 6.5,
                power = 7.0,
                stamina = 6.0
            )
        ),
        Character(
            name = "Rosalina",
            description = "A mysterious lady who travels the galaxy accompanied by her family of star-like creatures called Lumas.\n" +
                    "\n" +
                    "Rosalina may seem a bit distant, but she is actually quite kind-hearted. She is the adoptive mother of the Lumas. Though her home is among the stars, she'll sometimes joins Mario and friends on their adventures.",
            imageOpen = R.drawable.rosalina_open,
            imageClose = R.drawable.rosalina_closed,
            imageFull = R.drawable.rosalina_open_02,
            backgroundColor = arrayListOf(29, 213, 183),
            characterSound = arrayListOf(
                R.raw.sound_rosalina_0, R.raw.sound_rosalina_1, R.raw.sound_rosalina_2
            ),
            fullName = "Princess Rosalina",
            species = "Human",
            dialog = "May the starts shine down on you.",
            ability = Ability(
                acceleration = 7.0,
                maxSpeed = 6.0,
                technique = 8.0,
                power = 7.0,
                stamina = 6.0
            )
        ),
        Character(
            name = "Bowser Jr.",
            description = "The only son of Bowser, the King of the Koopas. He's often seen wearing a mask with an intimidating mouth drawn on it.\n" +
                    "\n" +
                    "Bowser Jr. is small but has inherited great strength from his father.\n" +
                    "\n" +
                    "He may throw a tantrum if things don't go his way. He's know to cause plenty of trouble and can be a bit selfish.",
            imageOpen = R.drawable.bowser_jr_open,
            imageClose = R.drawable.bowser_jr_closed,
            backgroundColor = arrayListOf(246, 206, 9),
            characterSound = arrayListOf(
                R.raw.sound_bowser_jr_0, R.raw.sound_bowser_jr_1, R.raw.sound_bowser_jr_2
            ),
            fullName = "Bowser Junior",
            species = "Koopa",
            dialog = "Someday... when I'm bigger... I wanna fight that Mario again!",
            ability = Ability(
                acceleration = 8.0,
                maxSpeed = 7.0,
                technique = 8.0,
                power = 6.5,
                stamina = 5.0
            )
        ),
        Character(
            name = "Boo",
            description = "Mischievous ghosts who often appear in dark, abandoned places.\n" +
                    "\n" +
                    "They may be spooky ghosts, but they're also incredibly shy. They will freeze in place and cover their eyes if someone looks right at them.",
            imageOpen = R.drawable.boo_open,
            imageClose = R.drawable.boo_closed,
            backgroundColor = arrayListOf(149, 164, 174),
            characterSound = arrayListOf(
                R.raw.sound_boo_0, R.raw.sound_boo_1, R.raw.sound_boo_2
            ),
            fullName = "Boo",
            species = "Boo",
            dialog = "This board is a scary little spook house that I cooked up a while back.",
            ability = Ability(
                acceleration = 8.0,
                maxSpeed = 8.0,
                technique = 5.0,
                power = 6.0,
                stamina = 5.0
            )
        ),
        Character(
            name = "Donkey Kong",
            description = "The king of the jungle whose trademark is his red necktie, which bears his initials.\n" +
                    "\n" +
                    "Donkey Kong can hurl giant barrels with the greatest of ease, and is so powerful the ground shakes when he pounds on the ground.\n" +
                    "\n" +
                    "He loves bananas and always keeps a large stash of them in his treehouse.",
            imageOpen = R.drawable.donkey_kong_open,
            imageClose = R.drawable.donkey_kong_closed,
            imageFull = R.drawable.donkey_kong_open_02,
            backgroundColor = arrayListOf(116, 38, 7),
            characterSound = arrayListOf(
                R.raw.sound_donkey_kong_0, R.raw.sound_donkey_kong_1, R.raw.sound_donkey_kong_2
            ),
            fullName = "Donkey Kong",
            species = "Kong",
            dialog = "My bananas and my buddy, Diddy Kong, they are gone! The Kremlings will pay! " +
                    "I'll hunt them down through every corner of my island, until I have every last banana from my hoard back!",
            ability = Ability(
                acceleration = 6.0,
                maxSpeed = 8.0,
                technique = 7.0,
                power = 6.0,
                stamina = 6.5
            )
        ),
        Character(
            name = "Diddy Kong",
            description = "Donkey Kong's trusted friend and partner. His trademarks are his red shirt with yellow stars and red cap.\n" +
                    "\n" +
                    "Though not as strong as DK, Diddy Kong is agile and a great jumper. He's fast as well, and a great ally for " +
                    "helping DK protect his banana stash.",
            imageOpen = R.drawable.diddy_kong_open,
            imageClose = R.drawable.diddy_kong_closed,
            backgroundColor = arrayListOf(152, 49, 6),
            characterSound = arrayListOf(
                R.raw.sound_diddy_kong_0, R.raw.sound_diddy_kong_1, R.raw.sound_diddy_kong_2
            ),
            fullName = "Diddy Kong",
            species = "Kong",
            dialog = "I am the brave Diddy Kong. Show yourself, if you are friently. If not, " +
                    "then get ready for the fight of your life!",
            ability = Ability(
                acceleration = 6.0,
                maxSpeed = 8.0,
                technique = 5.5,
                power = 8.0,
                stamina = 7.0
            )
        ),
    )

    fun news(): ArrayList<News> {
        return arrayListOf(
            News(
                title = "Discover the all-new course Piranha Plant Cove with the Exploration Tour",
                headline = "Discover a brand-new course in the Mario Kart™ Tour game with the Exploration Tour, the latest limited-time event in the Mario Kart Tour game, happening now.",
                link = "https://mario.nintendo.com/news/mobilenews-discover-the-all-new-course-piranha-plant-cove-with-the-exploration-tour/",
                image = R.drawable.news_01,
                date = "02.10.2023"
            ),
            News(
                title = "Wii Rainbow Road blasts off in the Space Tour",
                headline = "Three…two…one…liftoff! Launch into galactic kart-racing fun with the Space Tour, the latest limited-time event in the Mario Kart Tour game, happening now.",
                link = "https://mario.nintendo.com/news/mobilenews-wii-rainbow-road-blasts-off-in-the-space-tour/",
                image = R.drawable.news_02,
                date = "01.12.2023"
            ),
            News(
                title = "Happy New Year’s Tour!",
                headline = "Put 2022 in your rear-view mirror and race into 2023 with the New Year’s Tour, the latest limited-time event in the Mario Kart Tour game, happening now!",
                link = "https://mario.nintendo.com/news/happy-new-years-tour/",
                image = R.drawable.news_03,
                date = "12.29.2022"
            )
        )
    }
}
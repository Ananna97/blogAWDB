package com.example.blog.config;

import com.example.blog.model.*;
import com.example.blog.repository.RoleRepository;
import com.example.blog.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SeederClass implements CommandLineRunner
{
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final CategoryService categoryService;
    private final PostService postService;
    private final RatingService ratingService;
    private final CommentService commentService;

    public SeederClass(PostService postService, RatingService ratingService, CategoryService categoryService, UserService userService, RoleRepository roleRepository, CommentService commentService) {
        this.postService = postService;
        this.ratingService = ratingService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.commentService = commentService;
    }


    @Override
    public  void run (String... args) {
        List<User> users = userService.findAll();
        User u = new User();
        User u1 = new User();
        User u2 = new User();
        User u3 = new User();
        if (users.isEmpty()) {


            Role user = new Role();
            user.setName("ROLE_USER");
            roleRepository.save(user);

            Role admin = new Role();
            admin.setName("ROLE_ADMIN");
            roleRepository.save(admin);

            u.setFirstName("admin");
            u.setLastName("admin");
            u.setEmail("admin.admin@email.com");
            u.setPassword("admin");
            Set<Role> authoritiesAdmin = new HashSet<>();
            roleRepository.findById("ROLE_ADMIN").ifPresent(authoritiesAdmin::add);
            u.setRoles(authoritiesAdmin);
            userService.save(u);

            u1.setFirstName("Maria");
            u1.setLastName("Sinna");
            u1.setEmail("maria.sinna@email.com");
            u1.setPassword("Ciao1");
            Set<Role> rolesUser = new HashSet<>();
            roleRepository.findById("ROLE_USER").ifPresent(rolesUser::add);
            u1.setRoles(rolesUser);
            userService.save(u1);

            u2.setFirstName("Ella");
            u2.setLastName("Fondly");
            u2.setEmail("ella.fondi@email.com");
            u2.setPassword("Hello2");
            u2.setRoles(rolesUser);
            userService.save(u2);

            u3.setFirstName("Lori");
            u3.setLastName("Poji");
            u3.setEmail("lori.poji@email.com");
            u3.setPassword("Bonjour3");
            u3.setRoles(rolesUser);
            userService.save(u3);
        }

        List<Category> categories = categoryService.findAll();
        Category c1 = new Category();
        Category c2 = new Category();
        Category c3 = new Category();
        if (categories.isEmpty()) {
            c1.setCategoryName("Good Food");
            categoryService.save(c1);

            c2.setCategoryName("Fun Facts");
            categoryService.save(c2);

            c3.setCategoryName("Let's have fun!");
            categoryService.save(c3);
        }

        List<Post> posts = postService.findAll();
        Post p1 = new Post();
        Post p2 = new Post();
        Post p3 = new Post();

        if (posts.isEmpty()) {
            p1.setTitle("Barcelona food guide: What to eat?");
            p1.setBody("Spain is home to a myriad of delicious traditional and unique dishes. While each region of Spain has a unique cuisine, Barcelona's the ultimate destination. Not only is Barcelona one of Spain’s best cities to live in but also one of the best Spanish cities for food." +
                    "" +
                    "As you walk through the streets of the city, you’ll get to explore the unique and tasteful Catalan cuisine. And if you’re a foodie, your taste buds will thank you for the drool-worthy and lip-smacking tastes they’re about to discover." +
                    "" +
                    "With thousands of eateries spread across the streets of Barcelona, it’s nearly impossible to judge the absolute best. So to help you narrow it down, we’re sharing some of the local approved restaurants in Barcelona where you can devour local Catalan dishes." +
                    "" +
                    "Watch out for tourist traps" +
                    "Every year Barcelona welcomes millions of visitors and not surprisingly, there’re plenty of tourist traps scattered around. To get a real feel and taste of Catalan food, prioritise going to these top places where you can eat authentic local food in Barcelona." +
                    "" +
                    "As Barcelona's nestled in Catalonia, it’s got quite a different gastronomical taste than the rest of Spain. Locals insist that you can’t leave Barcelona without trying these top 15 Catalonian dishes:" +
                    "" +
                    "Fideuà- Fideuà's paella but with noodles. Unlike the original paella from Valencia, a fideuà has lots of seafood and shellfish as Barcelona's close to the coast." +
                    "" +
                    "Pa amb Tomàquet- Grilled bread brushed with garlic, topped with tomato relish and seasoned with pepper, salt, and olive oil. Perfect for any mealtime." +
                    "" +
                    "Esqueixada- This light, refreshing, ceviche style Catalan salad's made with raw salted cod (bacalao), tomatoes, onions, romesco sauce and black olives." +
                    "" +
                    "Escalivada- Escalivada's made of grilled eggplant, red peppers, garlic, salt and olive oil. Optionally, anchovies or sardines can be added. It’s perfect as tapas or as a topping on another dish." +
                    "" +
                    "Suquet de Peix- This traditional Catalan potato-based fish stew used to be made with fresh fish that was unsold at the market. Today, you can even find bell-pepper powder, saffron, and picada, a special Catalan sauce to give this hearty dish a touch of elegance." +
                    "" +
                    "Escudella d’Olla- Also known as Sopa de Galets's a popular Christmas dinner dish in Catalonia. It’s a 2-part dish. The first's a hearty broth made of various types of meat. The broth or soup's served with snail-shaped pasta (Escudella). The second or main dish (d’Olla) is a platter of meats, vegetables and chickpeas." +
                    "" +
                    "Arròs Negre- Here’s an eye-catching dish. Squid ink is used to flavour and colour the rice black. This black rice is then served with squid and aioli." +
                    "" +
                    "Mandonguilles amb Sípia- A unique dish that combines stewed meatballs with cuttlefish. In fact, since Catalnonian’s love combining ingredients this combination of meat and seafood is officially called mar i montaña (sea and mountain)." +
                    "" +
                    "Botifarra amb Mongetes- White beans or haricot beans served with sausage (botifarra). Traditionally a hearty farmers dish that's perfect for lunch." +
                    "" +
                    "Cargols a la llauna- Cargols (snails) are a delicacy in this part of Spain and they’re often cooked in an oven on a tin tray with sauces or vinaigrette." +
                    "" +
                    "Canelons- Canneloni stuffed with stewed meat's traditional in Barcelona. It’s primarily served during Christmas when leftover meat from Christmas dinner is wrapped in cannelloni and topped with Bechamel sauce." +
                    "" +
                    "Espinacs amb pansies i pinyons- This Catalan dish combines savoury with sweetness brilliantly. Spinach's fried in olive oil with raisins and toasted pine nuts.");
            p1.setCategory(c1);
            p1.setUser(u1);
            postService.save(p1);

            p2.setTitle("The Seven Secrets Of Bologna");
            p2.setBody("What Are The Seven Secrets Of Bologna?" +
                            "            The Seven Secrets of Bologna are hidden in its historic centre, all within an easy walk from each other. As with all the very best secrets, no one really knows where the idea came from or who “invented” them." +
                    "                    For those who have lived in Bologna for a long time, these secrets are not such a surprise. However, for tourists visiting the city for the first time, there is nothing better than going on an exciting “secrets hunt”." +
                            "            1. Little Venice" +
                            "            Bologna sits on top of a network of canals. These are mostly underground, and were built in the 12th century. One of them closely resembles the canals in Venice." +
                            "            In Via Piella is a little window that opens onto the charming Canale delle Moline, known as “Little Venice”. The window is usually closed, so you will need to push aside the shutter to look out onto the Venice-like canal." +
                            "            In fact, this secret is not much of a secret any more as it has become one of the most popular tourist attractions in Bologna!" +
                            "            2. The Four Corners Of The Podestà" +
                            "            The most fun of the Seven Secrets of Bologna is the “wireless phone” of Palazzo del Podestà. This building has an extraordinary architecture that allows sounds to be transmitted from one corner of the archway to the opposite side." +
                            "            Turn your face to the wall and whisper, and whoever is facing the wall on the opposite corner will hear you loud and clear! To play the whispers game, go to the central Piazza Maggiore and walk to the archway at the centre of Palazzo del Podestà." +
                            "            According to tradition this place was used by lepers in the Middle Ages to confess their sins without getting into direct contact with the priests." +
                            "            3. The Finger Of Neptune’s Statue" +
                            "            A famous statue in Bologna is of the god Neptune in Piazza Nettuno, beside Piazza Maggiore. Stand behind the statue and search on the floor for the “tile of shame”, a black stone placed near the staircase to Sala Borsa." +
                            "            If you stand on this specific spot, it will appear that Neptune has an erection! However, this is just an optical illusion created by the finger on the statue’s left hand. Legend states that Giambologna, who sculpted the fountain in the 16th century, created the statue in this way as a revenge against the Vatican." +
                            "            4. The Three Arrows" +
                            "            The next secret is located in Strada Maggiore, in front of the entrance of Corte Isolani. The building has a wooden roof with three arrows stuck in it. They are not easy to gaze at as the ceiling is quite high." +
                    "                    The legend is that one night some criminals were sent to assassinate a local person. However a woman, who was standing undressed in front of a window, noticed them and started screaming. Surprised by the woman’s nakedness, and fearing being caught, the criminals misfired and shot their arrows into the roof." +
                            "            5. A Historical Reference To Cannabis" +
                            "            At the end of Via Indipendenza, where it meets Via Rizzoli, the ceiling of the archway hides another secret: writing in Latin that says “Panis vita, canabis protectio, vinum laetitia”. This phrase translates as “Bread is life, cannabis is protection, wine is joy.”" +
                    "            This historical reference to cannabis may surprise you. However in the Middle Ages the plant was used as a textile, and its trade contributed to Bologna’s wealth." +
                            "            6. The Broken Vase" +
                            "            This secret is concealed inside the very symbol of Bologna, the Torre degli Asinelli. Dominating Bologna’s skyline, the tower is at the end of Via Rizzoli. The climb is arduous as the 498 steep steps are nestled between narrow walls." +
                    "                    Once at the top of the tower, try to look for a broken vase. However, some people say there is no vase at all… To see if you can discover it for yourself, you first need to book your entrance to the Torre degli Asinelli (there is a €5 entrance charge)." +
                            "            7. Panum Resis" +
                            "            The last secret is right in the heart of the university neighbourhood. On Via Zamboni is Palazzo Poggi, where it is said a desk bears the engraving “Panum Resis”, meaning that knowledge is the foundation of every choice. This is a tribute and a reference to the University of Bologna." +
                    "            Embark on the adventure of finding the engraved writing if you can. No-one has so far been able to find where this inscription is hidden, so it remains a secret!");
            p2.setCategory(c2);
            p2.setUser(u2);
            postService.save(p2);

            p3.setTitle("10 Ridiculously Fun Things To Do With Your Friends In Amsterdam");
            p3.setBody("It’s no secret that Amsterdam is a city bursting with activities and entertainment. Aside from the buzzing coffeeshops and the famous Red Light District, there are plenty more fun things to do with your friends and many things about Amsterdam that you should know about!" +
                    "" +
                    "From Amsterdam’s friendship cruises, playing glow-in-the-dark mini-golf or riding the highest swing in Europe; there are lots of great group activities to get stuck into during your city break. Whether you’re enjoying a lads weekend in Amsterdam, are on a hen do or are simply exploring the Dutch capital on your travels, we’ve rounded up the 10 top things to do in Amsterdam with your friends." +
                    "" +
                    "Table of Contents" +
                    "" +
                    "1. Take a ride on Europe’s Highest Swing" +
                    "" +
                    "2. Cruise like kings with Pure Boats" +
                    "" +
                    "3. Go street art hunting" +
                    "" +
                    "4. Tour the city by bike" +
                    "" +
                    "5. Hit a festival" +
                    "" +
                    "6. Go bargain hunting at IJ Hallen" +
                    "" +
                    "7. Head to the beach at Pllek" +
                    "" +
                    "8. Hail the disco taxi" +
                    "" +
                    " 9. Eat your way around the city" +
                    "" +
                    "10. Hang out in a cold war bunker");
            p3.setCategory(c3);
            p3.setUser(u3);
            postService.save(p3);
        }

        List<Rating> ratings = ratingService.findAll();
        Rating r1 = new Rating();
        Rating r2 = new Rating();
        Rating r3 = new Rating();
        if (ratings.isEmpty()) {
            r1.setValue(10);
            r1.setPost(p2);
            r1.setUser(u1);
            ratingService.save(r1);

            r2.setValue(7);
            r2.setPost(p3);
            r2.setUser(u3);
            ratingService.save(r2);

            r3.setValue(9);
            r3.setPost(p2);
            r3.setUser(u3);
            ratingService.save(r3);
        }

        List<Comment> comments = commentService.findAll();
        Comment co1 = new Comment();
        Comment co2 = new Comment();
        Comment co3 = new Comment();
        if (comments.isEmpty()) {
            co1.setText("I really enjoyed reading this post. I can’t wait to visit Bologna! <3");
            co1.setPost(p2);
            co1.setUser(u1);
            commentService.save(co1);

            co2.setText("I carve for some really fresh, fruity and sweet sangria!");
            co2.setPost(p3);
            co2.setUser(u3);
            commentService.save(co2);

            co3.setText("Interesting information and very helpful. I recommend with all my heart having a pistachio gelato at Cremeria Santo Stefano.");
            co3.setPost(p2);
            co3.setUser(u3);
            commentService.save(co3);
        }
    }
}
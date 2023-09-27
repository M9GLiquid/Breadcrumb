# Building a Reusable Game Framework in Java

![Date](https://img.shields.io/badge/Date-2023--05--26-green)  

**Authors:** Hieu Tran, Thomas Lundqvist, Rim Abdennour  
**Supervisor:** Wojciech Mostowski, Nesma Rezk.  
**Course:** Advanced Object Oriented Programming (DT4014)  
**Institution:** Halmstad University  

[**1. Introduction**](#introduction)  
[**2. Design**](#design)  
[**3. Testing**](#testing)  
&nbsp;&nbsp;[3.1. Unit Testing](#unit-testing)  
&nbsp;&nbsp;[3.2. Integration Testing](#integration-testing)  
[**4. Implementation Highlights**](#implementation-highlights)  
[**5. Results and Evaluation**](#results-and-evaluation)  
&nbsp;&nbsp;[5.1. Results](#results)  
&nbsp;&nbsp;[5.2. Evaluation](#evaluation)  
[**6. Version Control System**](#version-control-system)  
[**7. References**](#references)  

## Introduction
<p> The course, Advanced Object Oriented Programming (AOOP)* at Halmstad University, is based on* developing a software solution project for a grid-based game framework. The goal was to craft a versatile and efficient platform for game development, adhering to the specific project requirements. The project is named the *Breadcrumb Project*.

The name comes from the tale of Hansel and Gretel, where breadcrumbs served as markers to find their way home. In this context, Breadcrumb symbolizes a guide through the complexities of grid-based game development. The framework streamlines the coding process, simplifying the creation of complex games, and presents a clear, navigable path for developers.

This report presents the design, implementation, and testing of a software solution developed using a Version Control System (VCS)* to address the requirements outlined in the project specifications for a framework of grid-based games. Testing is essential to software development as it ensures the correctness of individual units (methods, functions, classes) in isolation.

The developed software aims to provide a framework for audio, Input/Output management, and menu- and entity-handling functionalities through design patterns such as Model View Controller (MVC)* and Observer-Subject (OS).

The current report discusses the decision-making process, highlights the software's key features, and compares it to similar existing programs.


## Design
The design principles for the framework was a commitment to modularity, reusability, and extensibility, guided by the implementation of Object-Oriented Programming (OOP) principles and design patterns, especially MVC and OS. This approach served several purposes:

- **Ease of Modification:** With clearly separated components (model, view, controller, observer, and strategy), the project could easily maintain, improve and enhance the code base to add the ability for the code to act more independently from the rest of the code base, allowing for parts of the system to be replaced and updated without impacting the rest of the systems.
- **Code Reusability:** The framework inherently promotes reusability and reduces code duplications across game implementations. By leveraging the functionality of code reusability, developers can accelerate their development pipeline. This allows the developer to focus on game-specific rules and functionalities<sup>[[1]](#f1)</sup>.
- **Extendability:** By focusing foremost on the framework and not a singular game, we ensured that our solution could easily be extended to incorporate other games.
- **Testing and Quality Assurance:** The well-organized structure of the framework streamlined the testing process. The design incorporated shared components, allowing easy reuse and tailor-specific testing scenarios. Consequently, this ensures the increased quality and reliability of the framework.

The following diagrams and code snippets provide an overview of our framework and how it was employed to create the Sokoban, Maze and 2048 games.

![](Aspose.Words.8ae296f0-4c30-4927-bad9-852c80ba204e.003.png)

<a name="jeuzxqgy5fej"></a><i><b>Figure 1:</b> The UML diagram shows our design clearly. The GameBoard and its parts are the Models. The Controller is part of the Strategy pattern. GameState, Audio, and Graphical observers keep track of game events to enhance gameplay<sup>[[2]](#f2)</sup>.</i>

```
public class StateManager implements GameObserver {
    private final Game game;

    public StateManager(Game game){
        this.game = game;
    }

    @Override
    public void update() {
        switch (game.getBoard().getState()) {
              case WIN, GAME\_OVER -> game.end();
              case INITIATE -> game.initiate();
              case LEVEL\_COMPLETE -> game.start();
              case RUN -> {}
              case RESET -> game.restart();
              case PAUSE -> game.pause();
        }
    }
}
```
```
public class ConsoleView implements GameObserver {

    private final GameBoard board;

    public ConsoleView(GameBoard board){
        this.board = board;
    }

    @Override
    public void update() {
        System.out.println(board);
    }
}
```

<a name="stkoo29d3kd"></a><i><b>Figure 2:</b> Creation of an Observer class<sup>[[3]](#f3)</sup>.</i>

```
getController().addStateObserver(
    new StateManager(this)
);

getController().addAudioObserver(
    new SokobanGameAudioObserver(this)
);

getController().addViewObserver(
    frame.getGameView()
);

getController().addViewObserver(
    new ConsoleView(getBoard())
);

notifyStateObservers();

notifyViewObservers();

notifyAudioObservers();
```

<a name="rcqxodwyi76h"></a><i><b>Figure 3:</b> (Left) Adding observers in Sokoban class and (Right) how to update on an event<sup>[[4]](#f4)</sup>.</i>

As illustrated in [Figure 2](#stkoo29d3kd) and [Figure 3](#rcqxodwyi76h), implementing an observer, registering it with the event listeners, and subsequently generating an event is a straightforward process in the framework. [Figure 1](#jeuzxqgy5fej) provides a detailed view through a UML diagram, effectively showcasing the interdependencies and interactions among these components, illuminating their cohesive operation within the system.
## Testing
As mentioned before, the purpose of the testing is to ensure the correctness of individual units, such as methods, functions, and classes, in partial or complete isolation. During the project process, several tests were made after the created method for the software was accomplished. The tests are organized into individual cases where each focuses on a specific aspect or scenario of the specific method.

Automatic testing techniques were employed using JUnit 5, AssertJ, and Mockito frameworks. JUnit enabled automated test execution for consistent results. AssertJ is used to enhance the readability and expressiveness of the assertions. Mockito simulated dependencies, allowing isolated testing of specific class methods. By mocking dependencies, tests focused solely on the class behavior, independent of external factors. This approach ensured independent and reproducible tests, enhancing reliability<sup>[[10]](#f10)</sup><sup>[[11]](#f11)</sup><sup>[[12]](#f12)</sup>.


```
class Board2048Test {
    Board2048 underTest;
    Game2048 game;
    @BeforeEach
    void setUp(){
        game = mock(Game2048.class);
        Board2048 board = new Board2048(game, 6, 6);
        underTest = Mockito.spy(board);
        doNothing().when(underTest).addNewTile();
        doNothing().when(underTest).addNewTile();
    }
}
```
<a name="kix.4wbjyvdxv1aw"></a><i><b>Figure 4:</b> A setUp() method for the board test class of the game 2048<sup>[[5]](#f5)</sup>.</i>

In every test file, a setup method is mandatory to construct the specified arguments adjusted for the class the test is conducted for. This was done with the help of mocking classes and spying on the objects. The creation of a spy object of the ´Board2048´ class is to get access to its local methods<sup>[[6]](#f6)</sup>. The different statements, such as doNothing(), doReturn(), and more, were used to test the code. This can be seen in [Figure 4](#kix.4wbjyvdxv1aw).
### Unit Testing
For this project, unit testing was prioritized over integration testing. Unit tests are often simpler to write and easier to read, making them a practical choice. This strategy aimed to boost the reliability and stability of individual components, enhancing the overall integrity of the project's game framework.


```
public void makeMove(Location direction) {
    if(direction.getX() == 1){
        transpose();
        reverse();
        compress();
        merge();
        compress();
        reverse();
        transpose();
    }
    else if(direction.getX() == -1){
        transpose();
        compress();
        merge();
        compress();
        transpose();
    }
    else if(direction.getY() == 1){
        reverse();
        compress();
        merge();
        compress();
        reverse();
    }
    else if(direction.getY() == -1){
        compress();
        merge();
        compress();
    }
    if(isGameOver())           
        game.getBoard().setState(GameState.GAME_OVER);                    
        game.getController().notifyAudioObservers();
    addNewTile();
}
```

```
void test_makeMove_right() {
    // Given
    Location right = new Location(1, 0);
    
    GameStrategy controller = mock(GameStrategy.class);
    doReturn(controller).when(game).getController();    
    doNothing().when(controller).notifyAudioObservers();
    doReturn(false).when(underTest).isGameOver();
    
    doNothing().when(underTest).transpose();
    doNothing().when(underTest).compress();
    doNothing().when(underTest).merge();
    doNothing().when(underTest).reverse();
    
    // When
    underTest.makeMove(right);
    
    // Then
    verify(underTest, times(2)).transpose();
    verify(underTest, times(2)).reverse();
    verify(underTest).merge();
    verify(underTest, times(2)).compress();
    verify(underTest).addNewTile();
    verify(controller).notifyAudioObservers();
}
```


<a name="kix.ty1jslpbgngx"></a>**Figure 5:** (Left) The makeMove() method is for making a move in the specified direction of the gameboard. (Right) The unit test for the makeMove() method to test the move in the right direction<sup>[[7]](#f7)</sup>.

The framework was tested in five main parts: audio, core, entities, Input/Output (IO)*,* and models. Tests in the core were made to check the possibility to enter the next level of a game, level completion and clearing entities. Testing the entity was done by setting the entity in different positions, including null. This was done because the entities should be movable. Tests for the different controller inputs and their respective scenarios were done making sure that the key’s corresponded with the correct move direction, as seen in [Figure 5](#kix.ty1jslpbgngx). 
### Integration Testing
By testing the integration and interaction between these different methods, it aims to discover potential defects early. The primary objective of integration testing is to ensure the integrated system functions properly as a unified entity. This is done by confirming that the different components first work individually and then as a complete unit<sup>[[8]](#f8)</sup>. 


```
public void makeMove(Location direction) {
    if(direction.getX() == 1){
        transpose();
        reverse();
        compress();
        merge();
        compress();
        reverse();
        transpose();
    }
    else if(direction.getX() == -1){
        transpose();
        compress();
        merge();
        compress();
        transpose();
    }
    else if(direction.getY() == 1){
        reverse();
        compress();
        merge();
        compress();
        reverse();
    }
    else if(direction.getY() == -1){
        compress();
        merge();
        compress();
    }
    if(isGameOver())            
        game.getBoard().setState(GameState.GAME_OVER);                    
        game.getController().notifyAudioObservers();
    addNewTile();
}
```

```
void iTest_makeMove_right() {
    // Given
    Location right = new Location(1, 0);
    Entity block1 = new Block(new Location(1, 1), EntityIcon2048.E2);
    Entity block2 = new Block(new Location(1, 2), EntityIcon2048.E4);
    Entity block3 = new Block(new Location(1, 3), EntityIcon2048.E2);
    Entity block4 = new Block(new Location(1, 4), EntityIcon2048.E8);
    
    ArrayList<Entity> entities = new ArrayList<>();
    entities.add(block1);
    entities.add(block2);
    entities.add(block3);
    entities.add(block4);
    underTest.setEntities(entities);
    
    GameStrategy controller = mock(GameStrategy.class);
    doReturn(controller).when(game).getController();
    doNothing().when(controller).notifyAudioObservers();
    doReturn(false).when(underTest).isGameOver();
    
    // When
    underTest.makeMove(right);
    
    // Then
    assertThat(     underTest.getEntities().get(0).getLocation()    ).isEqualTo(new Location(4,1));
    assertThat(     underTest.getEntities().get(1).getLocation()    ).isEqualTo(new Location(4,2));
    assertThat(     underTest.getEntities().get(2).getLocation()    ).isEqualTo(new Location(4,3));
    assertThat(     underTest.getEntities().get(3).getLocation()    ).isEqualTo(new Location(4,4));
}
```


<a name="kix.mimblm36vod3"></a>**Figure 6:** (Left) The makeMove() method is for making a move in the specified direction of the gameboard. (Right) The integration test for the makeMove() method to test the move in the right direction<sup>[[9]](#f9)</sup>.
###
<a name="_r5v9h6c8gw62"></a>During the testing process, several integration tests were made to verify the collaboration of multiple methods in the software. An example can be seen in [Figure 6](#kix.mimblm36vod3), where the integration test is equivalent to the unit testing in [Figure 5](#jhufqzfk356h) for the makeMove() method. 

## Implementation Highlights
Applying the OS design pattern in the game framework was rewarding for the project. This pattern proved valuable when updating various views following alterations in the game state were needed. Moreover, it offered developers the advantage of efficiently implementing new event triggers. This solution streamlined the development process while maintaining the flexibility of the codebase.

In a standard game, multiple components, such as the Graphical User Interface* (GUI), sound effects, and game states, must be updated when the game changes. Traditionally, the game would need to have direct references to all these components and update each one manually, resulting in tightly-coupled and hard-to-maintain code.

The approach sidestepped the traditional challenge of manually updating various components in the games. It was possible to avoid creating standard hard-to-maintain code by focusing on efficient event-based updates. Instead, obtaining a smoother, more streamlined development process was possible. The effectiveness of this strategy is highlighted in [Figure 2](#stkoo29d3kd) and [Figure 3](#rcqxodwyi76h), where it is visualized how the implementation handles these updates.

The MVC design pattern was another tool that was found to be useful during the work process on the project. This pattern helped differentiate aspects of the game, such as game controls, keyboard inputs, mouse clicks, and network-based inputs, but also the outputs, console-based, and GUI view. By applying these patterns, it made it possible to add, remove, or change these controls and views without hassle which provided increased flexibility and ensured code organization for improved management.

One challenge in game development involves ensuring inclusive gameplay experiences that cater to diverse playstyles, no matter how it is wished to play, whether it is console, PC, or browser. With the Strategy pattern, it was possible to support different playing methods without having to rewrite the whole game. The developers could easily accommodate whether a player prefers to use the keyboard or the mouse in their individual games.




## Results and Evaluation
### Results
The developed software solution, a grid-based game framework implemented in Java, successfully addressed the project requirements and achieved the desired functionality. The framework demonstrates several key features, including reusability and extensibility to the course principles and design patterns such as MVC and OS.  

The framework facilitated the development of Sokoban and 2048 games, as shown in [Figure 1](#jeuzxqgy5fej). It divides the components (models, views, observers, controllers, strategies) to simplify modification, promote code reuse, and support expansion, thereby enabling the efficient creation of new grid-based games. The code is well-structured, enabling smooth integration of extra functionalities while minimizing disruptions to existing code and ensuring easy maintenance.

The testing phase of the project involved both unit testing and integration testing. Unit testing was prioritized to focus on individual units, such as methods, and classes, to ensure their correctness in isolation, the integration testing aimed to verify the interaction and collaboration between different methods within the software. The tests were successful and produced the expected results, ensuring the functionality and reliability of the code. The use of Mockito facilitated the mocking of dependency and allowed for isolated testing<sup>[[10]](#f10)</sup>.
### Evaluation
The developed framework demonstrates several strengths and advantages. Adopting design patterns, such as OS and MVC, proved valuable in achieving modularity, reusability, and extensibility. The MVC pattern helped differentiate game controls and views, providing flexibility and code organization. In contrast, the OS pattern facilitated efficient updates of various views based on game events.

The program's design encourages abstraction and separation of concerns, making isolating and modifying specific parts easier without affecting the overall system. This approach makes it possible to extend new functionality to the program. Regarding the program's extensibility, it has been designed flexibly to make it relatively easy to incorporate new functionality.

In some cases, a better implementation of a particular component is possible due to the program's design principles making modifications straightforward. By following modularization and adhering to solid principles, one can easily replace specific classes without disrupting the functionality of other program parts. The flexibility of the software enables ongoing improvement and the ability to adopt more optimized solutions when needed. It makes it easy and allows the developers to implement better solutions in the future due to the program's design and structure.

![](Aspose.Words.8ae296f0-4c30-4927-bad9-852c80ba204e.004.png)

<a name="lwl9ol3tyfjn"></a>**Figure 7**: Java Swing framework hierarchy diagram showcases the structure and illustrates the connection between the GUI components and containers for building interactive UI in Java applications<sup>[[11]](#f11)</sup>.

![](Aspose.Words.8ae296f0-4c30-4927-bad9-852c80ba204e.005.png)<a name="rbhnm0yx3zqb"></a>**Figure 8**: UML diagram representing Breadcrumb projects framework, showcasing the connection and structure of its components for building a GUI<sup>[[2]](#f2)</sup>.

To compare Breadcrumb [Figure 8](#rbhnm0yx3zqb) and the Java Swing framework (JSwing) [Figure 7](#lwl9ol3tyfjn), the JSwing has its components more organized into classes and subclasses based on its functionalities. JSwing is more of a rich set of GUI components. Meanwhile, the Breadcrumb projects framework suggests a different structure. The structure is followed by specific requirements and design principles and therefore built after that.

For customizability, the JSwing enables developers to extend and modify their different components to suit their requirements. To some level, Breadcrumb allows developers to do the same, although some methods could be added to the different framework classes that could be used for future game developments.

Overall, the project results indicate the successful development of a reusable game framework in Java, showcasing the efficient creation of grid-based games. The code's organization facilitates the seamless integration of new features, ensuring maintainability. The software architecture and design choices enable easy modification and extension. Adhering to best practices, the program remains adaptable for future enhancements without compromising stability or existing functionality.
## Version Control System
As Breadcrumb started, the team chose GitHub as the primary VCS and collaborative development platform<sup>[[12]](#f12)</sup>. At the outset, the collective knowledge of GitHub was fundamental. The nuances of navigating this platform soon posed several challenges, notably the occasional misstep of committing changes to the wrong branches. This necessitated the need to address and resolve merge conflicts, revert commits and more<sup>[[13]](#f13)</sup><sup>[[14]](#f14)</sup><sup>[[15]](#f15)</sup><sup>[[16]](#f16)</sup><sup>[[17]](#f17)</sup><sup>[[18]](#f18)</sup>.

To manage the GitHub workflow, the project was incorporated through IntelliJ IDEA, an Integrated Development Environment (IDE) with its built-in VCS handler. The integration of the tools made it possible to manipulate version control tasks without leaving the coding environment, streamlining the development process. IntelliJ's interactive interface helped to resolve conflicts and manage the project's GitHub repositories directly from the IDE, adding a layer of convenience to the workflow.

As Breadcrumb progressed, a clear understanding emerged regarding the importance of proper branch management and merge strategies. With the practice of using pull requests for peer reviews, an environment of shared learning and continuous improvement was fostered. This approach was beneficial in elevating the code quality and enriched the collective knowledge of VCS.

Despite an initially steep learning curve, the journey with GitHub and IntelliJ IDEA has proven incredibly valuable. The experience navigating the complexities of GitHub, complemented by the efficiencies provided by IntelliJ's VCS handler, not only facilitated effective collaboration, comprehensive code management and fostered a sense of responsibility and ownership among team members. Consequently, this robust combination of tools has become integral to the project development.


## References
<b id="f1">1.</b> O’Grady, B. (n.d). What is a Framework? Why We Use Software Frameworks. Code Institute. [Online]. Available at: <https://codeinstitute.net/se/blog/what-is-a-framework/> (Accessed: 20 May 2023).  
<b id="f2">2.</b> Lundqvist, T. (2023). UML Diagram.  
<b id="f3">3.</b> Lundqvist, T. (2023). Observer Class.  
<b id="f4">4.</b> Lundqvist, T. (2023). Observer controllers.  
<b id="f5">5.</b> Tran, H. (2023). setUp() for the testing.  
<b id="f6">6.</b> Paraschiv, E. (20 May, 2023). Mockito – Using Spies. Baeldung. [Online]. Available at: <https://www.baeldung.com/mockito-spy> (Accessed: 22 May 2023).  
<b id="f7">7.</b> Tran, H. (2023). Unit Testing.  
<b id="f8">8.</b> Integration Testing. (n.d). JavaTPoint. [Online]. Available at: <https://www.javatpoint.com/integration-testing> (Accessed: 15 May 2023).  
<b id="f9">9.</b> Tran, H. (2023). Integration Testing.  
<b id="f10">10.</b> Mockito. (n.d). Mockito JavaDocs. [Online]. Available at: [https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html ](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)(Accessed: 26 May 2023).  
<b id="f11">11.</b> Assert. (n.d). Assert JavaDocs. [Online]. Available at: <https://junit.org/junit4/javadoc/4.13/org/junit/Assert.html> (Accessed: 26 May 2023).  
<b id="f12">12.</b> JUnit. (n.d). JUnit JavaDocs. [Online]. Available at: [https://junit.org/junit5/docs/current/api/ ](https://junit.org/junit5/docs/current/api/)(Accessed: 26 May 2023).  
<b id="f13">13.</b> JavaTPoint. (2023). Java Swing Framework Hierarchy Diagram. Available at: <https://www.javatpoint.com/java-swing> (Accessed 26 May 2023).  
<b id="f14">14.</b> GitHub. (n.d.). Hello World. GitHub Docs. Retrieved from <https://docs.github.com/en/get-started/quickstart/hello-world>  
<b id="f15">15.</b> JetBrains. (n.d.). Commit and Push Changes. IntelliJ IDEA Help. Retrieved from <https://www.jetbrains.com/help/idea/commit-and-push-changes.html>  
<b id="f16">16.</b> JetBrains. (n.d.). Sync with a Remote Repository. IntelliJ IDEA Help. Retrieved from <https://www.jetbrains.com/help/idea/sync-with-a-remote-repository.html>  
<b id="f17">17.</b> JetBrains. (n.d.). Undo Changes. IntelliJ IDEA Help. Retrieved from <https://www.jetbrains.com/help/idea/undo-changes.html>  
<b id="f18">18.</b> JetBrains. (n.d.). Using Git Integration. IntelliJ IDEA Help. Retrieved from <https://www.jetbrains.com/help/idea/using-git-integration.html>  

---
layout: page
title: Developer Guide
---
## **Table of Content**
- [**Acknowledgements**](#acknowledgements)
- [**Setting up, getting started**](#setting-up-getting-started)
- [**Design**](#design)
  - [Architecture](#architecture)
  - [UI component](#ui-component)
  - [Logic component](#logic-component)
  - [Model component](#model-component)
  - [Storage component](#storage-component)
  - [Common classes](#common-classes)
- [**Implementation**](#implementation)
  - [Dynamic Ui Rendering](#dynamic-ui-rendering)
    - [Design Consideration](#design-consideration)
    - [Implementation](#implementation-1)
  - [Create](#create)
    - [Design Consideration](#design-consideration-1)
    - [Implementation](#implementation-2)
    - [Usage](#usage)
  - [View](#view)
    - [Design Consideration](#design-consideration-2)
    - [Implementation](#implementation-3)
    - [Usage](#usage-1)
  - [Safe Delete](#safe-delete)
    - [Design Consideration](#design-consideration-3)
    - [Implementation](#implementation-4)
    - [Usage](#usage-2)
  - [Find](#find)
    - [Design Consideration](#design-consideration-4)
    - [Implementation](#implementation-5)
    - [Usage](#usage-3)
  - [Summary](#summary)
    - [Design Consideration](#design-consideration-5)
    - [Implementation](#implementation-6)
    - [Usage](#usage-4)
- [**Documentation, logging, testing, configuration, dev-ops**](#documentation-logging-testing-configuration-dev-ops)
- [**Appendix: Requirements**](#appendix-requirements)
  - [Product scope](#product-scope)
  - [User stories](#user-stories)
  - [Use cases](#use-cases)
  - [Non-Functional Requirements](#non-functional-requirements)
  - [Glossary](#glossary)
- [**Appendix: Instructions for manual testing**](#appendix-instructions-for-manual-testing)
  - [Launch and shutdown](#launch-and-shutdown)
  - [Deleting a person](#deleting-a-person)
  - [Saving data](#saving-data)

---

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

---

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

---

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2122S2-CS2103T-T11-1/tp/tree/master/docs/diagrams) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2122S2-CS2103T-T11-1/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2122S2-CS2103T-T11-1/tp/blob/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />


### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2122S2-CS2103T-T11-1/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2122S2-CS2103T-T11-1/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2122S2-CS2103T-T11-1/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2122S2-CS2103T-T11-1/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2122S2-CS2103T-T11-1/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2122S2-CS2103T-T11-1/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

---

## **Implementation**

The sections below give more details on how the following features are implemented.
* Dynamic Ui Rendering
* Create
* View
* Safe Delete
* Find
* Summary (WIP)

<!-- Clement -->
### Dynamic Ui Rendering
#### Design Consideration
WIP

#### Implementation
WIP - Insert UML

<!-- Joey -->
### Add
The add mechanism is facilitated by `MedBook`. It allows users to create and store records belonging to a patient.
These records include a `Patient`'s `Contact` details, `Medical` information, `Consultation` notes,`Prescription` and
`TestResult`. For each of the records, there is a corresponding class to add the record into the `MedBook`. 

Example: `TestResult` can be added into the `MedBook` using the `AddTestResultCommand` which allows users to add a new 
and unique record regarding the results of a medical test taken if this record is not a duplicate.

It extends the abstract class `Command` and has additional fields to store the patient's `NRIC` and their
corresponding records.

It implements the abstract method `execute` to add and store the record in `MedBook` and this operation is exposed in
the `LogicManager` class.
#### Design Consideration
Aspect: How add executes:
* Alternative 1: Extends `AddCommand` class
  * Pros: easy to follow AB3's OOP design
  * Cons: more code is needed to make the subclasses compatible with `AddCommand` and this design may also violate the
    Liskov Substitution Principle since the subclasses are more restrictive
* Alternative 2 (current choice): Extends `Command` class
  * Pros: easy to implement because the abstract class `Command` only has one abstract method
  * Cons: commands that aim to do the same thing (add records to the `MedBook`) are not grouped together and this may
    not be an intuitive approach to OOP
#### Implementation
<img src="images/AllAddCommandsClassDiagram.png" width="550" />

#### Usage
Given below is an activity diagram which shows the example usage scenario for when a user adds the `Medical` information
for a `Patient` and how the add mechanism behaves at each step:

<img src="images/AddMedicalActivityDiagram.png" width="550" />




<!-- Justin -->
### View
#### Design Consideration
##### Aspects: How view executes:
* Alternative 1 (current choice): type in the type, along with the arguments that specify view boundaries
    * Pros: Straightforward to implement, works well.
    * Cons: Cumbersome to type out, esp if a lot of parameters are needed.


#### Implementation
The view mechanism is facilitated by `MedBook`. It allows users to view various items within the provided parameters 
issued in the command. This is achieved by using a filtered list for each type, and then filtering the list 
according to the given parameters. These types include a `Patient`'s `Contact` details, `Medical` information, 
`Consultation` notes,`Prescription` and `TestResult`. For each of the records types, there is a corresponding class 
to view the record types into the `MedBook`.

The view mechanism is facilitated by `CommandType`. It implements `CommandType#parseViewCommandType()` which parse the
type of view command, and the arguments passed in the command string, which specify the boundaries of which elements to
include in the filtered list. From here, let `XXX` be the type determined from `CommandType#parseViewCommandType()`.
It then creates a new instance of `ViewXXXCommandParser`, which parses the view command
according to how the type in meant to be parsed, and then creates a new instance of `ViewXXXCommand`, which extends
Command. It implements the abstract method `execute` to invoke `Model` and update `filteredXXXList` in `MedBook`.
This operation is exposed in the `LogicManager` class. `UI` is then updated accordingly, displaying in the Main Window
the desired filtered list.

Example: `view t/consultation i/S1234567L` makes `MedBook` display the `filteredConsultationList` which
has been filtered beforehand according to the given parameter of patient ID S1234567L.

The following sequence diagram shows view consultation works:
<img src="images/ViewSequenceDiagram.png" />


#### Usage
Given below is an example usage scenario about how `view t/consultation` works:

Step 1: The user launches the application for the first time, The `CommandType` will be
initialized with default state, the is viewing the patient panel list.

Step 2: The user types in view t/consultation i/S1234567L. This command  will call `CommandType#parseVewCommandType` 
to parse what is the current type, which is consultation. 

From here, let `XXX` be the type determined in `CommandType#parseViewCommandType()`.

Step 3: It then creates a new instance of `ViewXXXCommandParser` and passes the arguments into the new instance.

Step 4: This then creates a new instance of `ViewXXXCommand`, which extends Command. Its `execute` method invokes 
Model's `Model#updateFilteredXXXList` to update the `filteredXXXList` according to the argument passed into the method.

Step 5: `UI` is then updated accordingly, displaying in the Main Window the desired filtered list.




<!-- Chee Kean -->
### Safe Delete
#### Design Considerations

##### Aspects: How delete executes:
* Alternative 1 (current choice): delete the index with the current view
  * Pros: Easy to implement
  * Cons: The user needs to view the field before he/she can perform the delete actions.
* Alternative 2: delete the field by specifying the NRIC, index and the type of field.
  * Pros: The user can delete any field at any page.
  * Cons: Harder to implement, it is not very intuitive for a user to delete a field without viewing it.

#### Implementation
The delete mechanism is facilitated bby `CommandType`. It extends MedBook
with the latest view state, stored internally as a currentViewType.
It implements `CommandType#parseDeleteCommandType()` which parse the type
of delete command according to the latest view type.

The following sequence diagram shows delete prescription works:
<img src="images/DeleteSequenceDiagram.png" />

#### Usage
Given below is an example usage scenario and how the delete prescription works:

Step 1: The user launches the application for the first time, The `CommandType` will be
initialized with default state, the is viewing the patient panel list.

Step 2: The user view t/prescription i/S1234567L, `Command Type` will change the `viewCommandType` to prescription type.

Step 3: The user decide to delete the 2nd prescription of this patient, The user executes `delete 2`
command. This command  will call `CommandType#parseDeleteCommandType` to parse what is the current type, which is
prescription. It will then delete the 2nd prescription from prescription model and save to storage


### Find
The `Find` command is used to find the patient whose names contain any of the given keywords. 

<!-- Joey -->
#### Design Consideration
WIP

#### Implementation

`FindCommandParser` parses input, builds a `NameContainsKeywordsPredicate` and returns a `FindCommand` with needed name predicate.

`FindCommand` extends the abstract class `Command`. 

It implements the abstract method `execute` to invoke `Model` and update the list of displayed patients in `MedBook`. 

This operation is exposed in the `LogicManager` class. 

`UI` is then updated accordingly.

WIP - Insert UML and activity diagram

#### Usage
WIP

<!-- Si Binh -->
### Summary

#### Design Consideration
Alternative 1 (current choice): Use the existing `updateFilteredXXXList(NRIC_PREDICATE)` methods in the `Model`. When the summary command is executed, update all existing filtered lists with NRIC predicate. Then update the `UI` using the existing filtered lists.

Pros:
- Easy to implement
- Less code duplication as we can reuse the logic that is used for filtering other entities

Cons:
- Increased coupling. For example, originally, `AddXXXCommand`s look for the patient in the latest filtered list of patients to add new entities to. Since at the summary screen the list is in the filtered state, we cannot add to other patients apart from the one being viewed. `AddXXXCommand`s had to be modified as a result.

Alternative 2: Having a dedicated model class `Summary`. A `Summary` object holds all needed information to be displayed. The `Summary` object is to be created by iterating over all existing unfiltered lists in `MedBook` to retrieve needed entities.

Pros:
- No coupling. Summary is not dependent on the state of the filtered lists

Cons:
- The logic to retrieve the needed entities is a bit of a duplicate
- One would need to manage the construction and flow of the `Summary` object and parse it for display, so more tedious to implement

#### Implementation
The viewing summary mechanism is implemented by modifying the existing `ViewCommand` and reusing the existing `Model#updateFilteredXXXList(NRIC_PREDICATE)` operations.

`ViewCommand` is augmented with an additional `nric` parameter. `ViewCommandParser` is enhanced to conditionally parse the NRIC parameter the user enters.
An additional operation `Model#updateSummary(nric)` is implemented. When `ViewCommand` calls `Model#updateSummary(nric)`, the method invokes the existing `Model#updateFilteredXXXList(NRIC_PREDICATE)` operations to update the state of the filtered lists in `Model`. The `UI` is then updated accordingly.

The following sequence diagram shows how the summary feature works:

<img src="images/SummarySequenceDiagram.png" />

The following activity diagram summarizes what happens when a user attempts to view a patient's summary

<img src="images/ViewSummaryActivityDiagram.png" width="550" />

#### Usage
Given below is an example usage scenario of how summary feature works:

Step 1: The user types in `view i/S1234567L`. This command will call `CommandType#parseViewCommandType`

Step 2: It then creates a new instance of `ViewCommandParser` and passes the arguments into the new instance

Step 3: This creates a new instance of `ViewCommand` with `nric` parameter

Step 4: `Model#updateSummary()` is invoked, which updates all filtered lists for the entities in `Model`

Step 5: `UI` is then updated accordingly, displaying the patient's summary

---
## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

---

## **Appendix: Requirements**

### Product scope

**Target user profile**:

Our target users would be doctors who need to keep track of their patient's medical information, health status and appointments.

**Value proposition**: MedBook is a health monitoring system for healthcare professionals that simplifies tracking a patient’s medical details and scheduling appointments. MedBook delivers a seamless workflow for doctors and healthcare professionals to search for or update patients' medical information, billing and appointments through a simple and easy-to-use platform.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​ | I want to …​                                                                  | So that I can…​                                              |
| -------- | ------- | ----------------------------------------------------------------------------- | ------------------------------------------------------------ |
| `* * *`  | user    | add, update and delete a patient's profile with their personal information    | keep track of my patients’ data                              |
| `* * *`  | user    | retrieve a patient's past diagnoses based on their name                       | have more information to make diagnoses                      |
| `* * *`  | user    | retrieve a patient's medicine administration history based on their name      | have more information to make diagnoses                      |
| `* * *`  | user    | view the progress of my patient's condition by viewing their health analytics | check if they are healing or getting better                  |
| `* * *`  | user    | to create patient medical information                                         | so that I can retrieve the record in the future efficiently. |
| `* * *`  | doctor  | make prescriptions (add, update and delete a medicine list and export it      |                                                              |
| `* * *`  | user    | I can add, update and delete a patient's test results                         | can make diagnoses                                           |
| `* * *`  | user    | retrieve a patient's visit history based on their name                        | have more information to make diagnoses                      |
| `* * *`  | user    | retrieve the contact details of the patient from the address book             | communicate with the patient effectively                     |
| `* * *`  | user    | retrieve patient's medical history based on a given date                      | pinpoint the patient's cause of disease more efficiently     |
| `* * *`  | user    | retrieve the medical history, information of the patient                      | assess the patient more accurately and quickly               |
| `* * *`  | user    | input my patient's information and medical history                            | store my patient’s medical data                              |
| `* * *`  | user    | delete patient's medical record                                               | better protect their privacy                                 |


### Use cases

(For all use cases below, the System is the MedBook and the Actor is the user, unless specified otherwise)

<!-- View a Patient’s Contact Information -->

<!-- Create a Patient’s Contact Information -->

Use Case: Delete a Patient’s Contact Information

**MSS**

1.  User requests to list patients
2.  MedBook shows a list of patients
3.  User requests to delete a specific patient in the list
4.  MedBook deletes the patient

    Use case ends.

**Extensions**

- 2a. The list is empty.

  Use case ends.

- 3a. 3a. The given NRIC is invalid.

  - 3a1. MedBook shows an error message.

    Use case resumes at step 2.

<!-- View a Patient’s Medical Information -->

<!-- Create a Patient’s Medical Information -->

<!-- Delete a Patient’s Medical Information -->

<!-- View a Patient’s Consultations Information -->

<!-- Create a Patient’s Consultations Information -->

<!-- Delete a Patient’s Consultations Information -->

<!-- View a Patient’s Prescriptions Information -->

<!-- Create a Patient’s Prescriptions Information -->

<!-- Delete a Patient’s Prescriptions Information -->

<!-- View a Patient’s Tests Result -->

<!-- Create a Patient’s Tests Result -->

<!-- Delete a Patient’s Tests Result -->


_{More to be added}_

### Non-Functional Requirements

1.  Should work on any mainstream OS as long as it has Java 11 or above installed.
2.  Should be able to hold up to 1000 patients without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

_{More to be added}_

### Glossary

- **Mainstream OS**: Windows, Linux, Unix, OS-X

---

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.
</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder
   
   2. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.
   
   2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

3. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.
   
   2. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   3. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   4. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

2. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

2. _{ more test cases …​ }_

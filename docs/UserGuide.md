---
layout: page
title: User Guide
---

Welcome to MedBook's user guide. This guide serves to equip users with the necessary knowledge to utilise all the features implemented in MedBook fully.
## Navigation Links

Click any links below to navigate into that section.

<div markdown="block" class="card" >
  <h7 markdown="span">[**Introduction**](#introduction)</h7>
  <div>
  This section contains information that serves to introduce you to MedBook.
  </div>
</div>

<div markdown="block" class="card" >
  <h7 markdown="span">[**Quick start**](#quick-start)</h7>
  <div>
  This section serves as a quick start guide that includes information on how to install, a tour of the graphical user interface and a getting started tutorial.
  </div>
</div>

<div markdown="block" class="card" >
  <h7 markdown="span">[**Commands**](#commands)</h7>
  <div>
  This section contains all the available commands in MedBook.
  </div>
</div>

<div markdown="block" class="card" >
  <h7 markdown="span">[**FAQ**](#faq)</h7>
  <div>
  This section contains all the frequently asked questions about MedBook.
  </div>
</div>

---

<div style="page-break-after: always;"></div>

## Introduction

<figure class="figure">
  <img class="logo" src = "images/user-guide/icon.png" width = "250" alt="Unable to load image! Try again later.">
</figure>

MedBook is a health monitoring system for healthcare professionals that simplifies tracking patients' medical
information and hospital records. MedBook delivers a seamless workflow for doctors and healthcare professionals to
search for or update patients' emergency contacts, medical information, medical test results, consultations and
prescriptions through a simple and easy-to-use platform.

---

<div style="page-break-after: always;"></div>

## Quick start

This section contains all the necessary information to get started with using MedBook.

### Requirement

- Ensure you have Java 11 or above installed on your computer.
- Download the latest `MedBook.jar` from [here](https://github.com/AY2122S2-CS2103T-T11-1/tp/releases).

### Setup

1. Copy the file to the folder you want to use as the home folder for MedBook.
2. Open MedBook through one of the following ways:
    * Double clicking `MedBook.jar` located in your MedBook home folder
    * In a command window, `cd` to your MedBook home folder and type `java -jar MedBook.jar`

<div style="page-break-after: always;"></div>

### Graphical User Interface

MedBook has an intuitive graphical user interface built for you to enjoy easy interaction with the application.
This section describes each part of the graphical user interface in detail.

<figure class="figure">
   <img src="images/GUI.png" alt="Graphical User Interface">
</figure>

| Label | Description                                                                                           |
| ----- |-------------------------------------------------------------------------------------------------------|
| 1     | **Toolbar**. This is where the settings allow you to exit the application and open the help window.   |
| 2     | **Command Input**. This is where you type the commands to be executed by the application.             |
| 3     | **Command Result**. This text box displays MedBook's response to the command entered.                 |
| 4     | **List of Patients**. This is the list of patients that are stored in the application.                |
| 5     | **Scroll Bar**. This scroll bar allows you to scroll for more information.                            |

### Controls

In order to improve your experience when using the app, we have included the following feature:

- Use the `↑` and `↓` arrow keys to navigate between previously entered commands in the command box.

### Getting Started
Follow this tutorial to get started with using MedBook:
1. Open MedBook through one of the following ways: 
   * Double clicking `MedBook.jar` located in your MedBook home folder 
   * In a command window, `cd` to your MedBook home folder and type `java -jar MedBook.jar`
2. If this is your first time using MedBook, you will see a prompt window requesting you to set up a new password.
3. Type in a password that fits the requirements and press `enter` to continue.

    <figure class="figure">
      <img src="images/SignUpScreen.png" alt="Password Prompt Display" width="350">
      <figcaption class="figure-caption text-right">Figure 1: Password Prompt Display</figcaption>
    </figure>

    <div style="page-break-after: always;"></div>

    <div markdown="block" class="alert alert-info">
    **:information_source: Notes about password:**<br>

    This password should only be known to you as it will be used to encrypt your MedBook data.
    See [FAQ](#faq) on how to reset your password.
    </div>

4. Once you have set up a password, you will be prompted to enter the same password every time you login.

   <figure class="figure">
       <img src="images/LoginScreen.png" alt="Login Prompt Display" width="350">
       <figcaption class="figure-caption">Figure 2: Login Prompt Display</figcaption>
   </figure>

5. After you have successfully logged in, the GUI will launch with some sample data and MedBook will look like the picture below.

   <figure class="figure">
       <img src="images/HomeScreen.png" alt="Login Prompt Display" width="500">
       <figcaption class="figure-caption">Figure 3: Main Display</figcaption>
   </figure>
6. You may refer to the command list below for the details of each available command.

---

<div style="page-break-after: always;"></div>

## Commands

This section provides an overview of all the available commands in MedBook. Click on any command below to see the complete details of the command.

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter that can be used as such `add n/John Doe`.

- Items in square brackets are optional.<br>
  e.g `n/NAME [tg/TAG]` can be used as `n/John Doe tg/diabetic` or as `n/John Doe`.

- Items with `…` after them can be used multiple times including zero times.<br>
  e.g. `[tg/TAG]… ` can be used as ` ` (i.e. 0 times), `tg/diabetic`, `tg/diabetic tg/hypertension` etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

- If a parameter is expected only once in the command but you specified it multiple times, MedBook will only register the last occurence.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `view`, and `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

<div style="page-break-after: always;"></div>

#### Patient Specific Commands
This section provides all the available commands to control patient's personal details.

| Commands                                               | Usage                     |
| :----------------------------------------------------- | :------------------------ |
| [`add i/NRIC`](#adding-a-patient-add)                  | Add a Patient             |
| [`view`](#viewing-all-patients-view)                   | View all Patients         |
| [`view i/NRIC`](#view-summary-of-a-patient-view-inric) | View Summary of a Patient |
| [`edit`](#editing-patient-information-edit)            | Edit a Patient            |

#### Contact Specific Commands
This section provides all the available commands to control patient's emergency contact list.

| Commands                                                       | Usage                    |
| :------------------------------------------------------------- | :----------------------- |
| [`add t/contact`](#adding-contact-information-add-tcontact)    | Add Contact Information  |
| [`view t/contact`](#viewing-contact-information-view-tcontact) | View Contact Information |
| [`edit`](#editing-contact-information-edit)                    | Edit Contact Information |

<div style="page-break-after: always;"></div>

#### Medical Information Specific Commands
This section provides all the available commands to control patient's medical information.

| Commands                                                       | Usage                    |
| :------------------------------------------------------------- | :----------------------- |
| [`add t/medical`](#adding-medical-information-add-tmedical)    | View Medical Information |
| [`view t/medical`](#viewing-medical-information-view-tmedical) | View Contact Information |
| [`edit`](#editing-medical-information-edit)                    | Edit Medical Information |

#### Consultation Specific Commands
This section provides all the available commands to control patient's consultation records.

| Commands                                                                   | Usage                         |
| :------------------------------------------------------------------------- | :---------------------------- |
| [`add t/consultation`](#adding-consultation-information-add-tconsultation) | Add Consultation Information  |
| [`view t/consultation`](#viewing-past-consultations-view-tconsultation)    | View Past Consultations       |
| [`edit`](#editing-consultation-information-edit)                           | Edit Consultation Information |

#### Prescription Specific Commands
This section provides all the available commands to control patient's prescription records.

| Commands                                                          | Usage             |
| :---------------------------------------------------------------- | :---------------- |
| [`add t/prescription`](#adding-prescription-add-tprescription)    | Add Prescription  |
| [`view t/prescription`](#viewing-prescription-view-tprescription) | View Prescription |
| [`edit`](#editing-prescription-edit)                              | Edit Prescription |

<div style="page-break-after: always;"></div>

#### Test Result Specific Commands
This section provides all the available commands to control patient's test results.

| Commands                                         | Usage            |
| :----------------------------------------------- | :--------------- |
| [`add t/test`](#adding-test-result-add-ttest)    | Add Test Result  |
| [`view t/test`](#viewing-test-result-view-ttest) | View Test Result |
| [`edit`](#editing-test-result-edit)              | Edit Test Result |

#### Non-specific Commands
This section provides all the available commands to control all patient's attributes mentioned above.

| Commands                               | Usage             |
| :------------------------------------- | :---------------- |
| [`help`](#viewing-help-help)           | View Help         |
| [`delete`](#deleting-any-entry-delete) | Delete any Entry  |
| [`find`](#finding-any-entry-find)      | Finding any Entry |


<div style="page-break-after: always;"></div>

### Adding a Patient: `add`

Adds a patient to MedBook.

Format: `add i/NRIC n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [tg/TAG]...`

<div markdown="block" class="alert alert-info">
**:information_source: Info:**<br>

Patient's NRIC must be a valid Singapore-issued NRIC. MedBook will only accept the following NRIC format:

1. NRIC consists of alphanumeric characters only.
2. NRIC starts with either S, T, F, G, or M character.
3. NRIC consists of 7 digits after the first character.
4. NRIC ends with any character.

Note: Since the checksum algorithm to validate the last character is not open-source, we only validate the formatting
of the NRIC.

See [here](https://en.wikipedia.org/wiki/National_Registration_Identity_Card) for more information about valid
Singapore NRIC.

NRIC **cannot be edited** in the future. Please ensure that you have keyed in the correct NRIC before proceeding.

</div>

Examples:

- `add i/S1234567L n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
- `add i/S1234568L n/Jane Doe p/98763488 e/janed@example.com a/311, Clementi Ave 2, #02-25 tg/diabetic tg/hypertension`

Below is the list of parameters that can be used with the `add i/S1234567L` command. 

| Parameters | Description                                                |
| ---------- | :--------------------------------------------------------- |
| `i/`       | _Required_. The NRIC associated with the patient.          |
| `n/`       | _Required_. The name associated with the patient.          |
| `p/`       | _Required_. The phone number of the patient.               |
| `e/`       | _Required_. The email address of the patient.              |
| `a/`       | _Required_. The address of the patient.                    |
| `tg/`      | _Optional_. The tags that are associated with the patient. |

### Viewing all Patients: `view`

Shows a list of patients in MedBook.

Format: `view`

### View Summary of a Patient: `view i/NRIC`

Shows a summary of a patient's information in MedBook, including their personal details, medical information,
consultation records, prescription records, results of medical tests taken and emergency contact information.

Format: `view i/NRIC`

Examples:

- `view i/S1234567L`

<figure class="figure">
  <img src="images/SummaryScreen.png" alt="Summary Screen Display" width="300" />
  <figcaption class="figure-caption text-right">Example of Summary Screen</figcaption>
</figure>

### Editing Patient Information: `edit`

Edits an existing patient's information entry in MedBook when a list of patients’ information entries is displayed.
This is except for the NRIC field, which cannot be modified after the creation of the Patient Information.
At least one field must be filled for the edit command to be valid.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [tg/TAG]...`

- Edits the patient at the specified INDEX. The index refers to the index number shown in the displayed patient list.
  The index must be a positive integer 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- When editing tags, the existing tags of the patient will be removed i.e editing of tags is not cumulative.
- You can remove all the patient's tags by typing tg/ without specifying any tags after it.

Examples:

- `view` followed by `edit 1 n/Alex Tan` updates the name of the first patient information entry displayed on the screen
  to Alex Tan.

<figure class="figure">
  <img src="images/EditScreen.png" alt="Edit Screen Result" width="350">
  <figcaption class="figure-caption text-right">Edit First Patient on Displayed List</figcaption>
</figure>


<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Editing a patient will overwrite the old data.
</div>

### Adding Contact Information: `add t/contact`

Adds a patient's emergency contact to MedBook.

Format: `add t/contact i/NRIC n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [tg/TAG]...`

- The NRIC specified here is the NRIC of the patient who has this emergency contact.

<div markdown="span" class="alert alert-info">:information_source: **Info:**
The NRIC specified here cannot be edited in the future.
</div>

<div markdown="block" class="alert alert-primary">
**:bulb: Tips:**

1. A contact can have any number of tags (including 0).
2. If you typed the wrong NRIC, `delete` the contact and `add` the contact again with the correct patient NRIC.
</div>

Examples:

- `add t/contact i/S1234567L n/Rihanna p/80008000 e/rihanna@gmail.com a/COM1 tg/primary tg/mother` adds an emergency
  contact Rihanna to the patient with NRIC, S1234567L.

Below is the list of parameters that can be used with the `add t/contact` command. 

| Parameters | Description                                                |
| ---------- | :--------------------------------------------------------- |
| `i/`       | _Required_. The NRIC of the patient to add the contact to. |
| `n/`       | _Required_. The name of the contact.                       |
| `p/`       | _Required_. The phone number of the contact.               |
| `e/`       | _Required_. The email address of the contact.              |
| `a/`       | _Required_. The address of the contact.                    |
| `tg/`      | _Optional_. The tags that are associated with the contact. |

<div style="page-break-after: always;"></div>

### Viewing Contact Information: `view t/contact`

Views a patient’s emergency contacts from MedBook.

Format: `view t/contact i/NRIC`

Examples:

- `view t/contact i/S1234567L`

### Editing Contact Information: `edit`

Edits an existing contact information entry in MedBook when a patient's list of contact information entries is being
displayed.
This is except for the NRIC field, which cannot be modified after the creation of the Contact Information.
At least one field must be filled for the edit command to be valid.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [tg/TAG]...`

- Edits the contact at the specified INDEX. The index refers to the index number shown in the displayed patient list.
  The index must be a positive integer 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- When editing tags, the existing tags of the patient will be removed i.e editing of tags is not cumulative.
- You can remove all the contact's tags by typing tg/ without specifying any tags after it.

Examples:

- `view t/contact` followed by `edit 1 n/John` updates the name of the first contact information entry displayed
  on the screen to John.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Editing a contact will overwrite the old data.
</div>

<div style="page-break-after: always;"></div>

### Adding Medical Information: `add t/medical`

Adds a patient's medical information to MedBook.

Format: `add t/medical i/NRIC [a/AGE] [bt/BLOOD_TYPE] [md/MEDICATION] [ht/HEIGHT] [wt/WEIGHT] [il/ILLNESSES] [su/SURGERIES] [fh/FAMILY_HISTORY] [ih/IMMUNIZATION_HISTORY] [gd/GENDER] [et/ETHNICITY]`

- We recommend inputting blood type using ABO blood group system (e.g. AB), height using centimeters (e.g. 185 cm), weight
  using kilograms (e.g. 70 kg), gender as either Male, Female or Non-binary, but we also allow any other system or special values.

Examples:

- `add t/medical i/S1234567L bt/AB ht/175 cm`
- `add t/medical i/S1234567L a/22 bt/O md/Paracetamol 500mg twice a day; Atarvastatin 20mg once a day ht/185 cm wt/70 kg il/Mild fever; High cholesterol su/Appendectomy fh/Has family history of high blood pressure ih/MMR; 6 in 1; Hepatitis B gd/Male et/Chinese`

Below is the list of parameters that can be used with the `add t/medical` command.

| Parameters | Description                                                          |
| ---------- | :--------------------------------------------------------------------|
| `i/`       | _Required_. The NRIC of the patient to add medical information to.   |
| `a/`       | _Optional_. The age of the patient.                                  |
| `bt/`      | _Optional_. The blood type of the patient.                           |
| `md/`      | _Optional_. Details about the patient's medication.                  |
| `ht/`      | _Optional_. The height of the patient.                               |
| `wt/`      | _Optional_. The weight of the patient.                               |
| `il/`      | _Optional_. Details about the patient's illnesses.                   |
| `su/`      | _Optional_. Details about the patient's surgeries.                   |
| `fh/`      | _Optional_. Details about the patient's family history.              |
| `ih/`      | _Optional_. Details about the patient's immunization history.        |
| `gd/`      | _Optional_. The gender of the patient.                               |
| `et/`      | _Optional_. The ethnicity of the patient.                            |

### Viewing Medical Information: `view t/medical`

Displays medical information of a patient from MedBook. If no NRIC is specified, MedBook will display all medical
information belonging to all patients.

Format: `view t/medical [i/NRIC]`

Examples:

- `view t/medical`
- `view t/medical i/S1234567L`

### Editing Medical Information: `edit`

Edits an existing medical information entry in MedBook when a list of medical information entries is displayed.
This is except for the NRIC field, which cannot be modified after the creation of the Medical Information.
At least one field must be filled for the edit command to be valid.

Format: `edit INDEX [a/AGE] [bt/BLOOD_TYPE] [md/MEDICATION] [ht/HEIGHT] [wt/WEIGHT] [il/ILLNESSES] [su/SURGERIES] [fh/FAMILY_HISTORY] [ih/IMMUNIZATION_HISTORY] [gd/GENDER] [et/ETHNICITY]`

Examples:

- `view t/medical` followed by `edit 1 bt/B` updates the blood type of the first medical information entry displayed on the screen.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Editing a medical information will overwrite the old data.
</div>

### Adding Consultation Information: `add t/consultation`

Adds a consultation report of a patient to MedBook.

Format: `add t/consultation i/NRIC dt/DATE tm/TIME dg/DIAGNOSIS fe/FEE [nt/NOTES]`

Examples:

- `add t/consultation i/S1234567L dt/2021-09-15 tm/18-00 dg/Inflammation in the throat and windpipe, short and shallow breath, laboured breathing. Most likely has Upper Respiratory Infection. fe/54.00 nt/Patient is having fever.`

Below is the list of parameters that can be used with the `add t/consultation` command.

| Parameters | Description                                                        |
|------------|:-------------------------------------------------------------------|
| `i/`       | _Required_. The NRIC of the patient to add medical information to. |
| `dt/`      | _Required_. The date of the Consultation.                          |
| `tm/`      | _Required_. The time of the Consultation.                          |
| `dg/`      | _Required_. The Diagnosis of the Consultation.                     |
| `fe/`      | _Required_. The Fee of the Consultation.                           |
| `nt/`      | _Optional_. The Additional Notes of the Consultation.              |

### Viewing Past Consultations: `view t/consultation`

Views all past consultations of a patient in MedBook.

Format: `view t/consultation i/NRIC`

Examples:

- `view t/consultation i/S1234567L`

### Editing Consultation Information: `edit`

Edits an existing consultation entry in MedBook when a list of consultation entries is displayed.
This is except for the NRIC field, which cannot be modified after the creation of the Consultation record.
At least one field must be filled for the edit command to be valid.

Format: `edit INDEX [dt/DATE] [tm/TIME] [dg/DIAGNOSIS] [fe/FEE] [nt/NOTES]`

Examples:

- `view t/consultation i/S1234567L` followed by `edit 1 dt/2019-08-10 tm/19-00` updates the date and time of the first
  consultation entry displayed on the screen.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Editing a consultation will overwrite the old data.
</div>

### Adding Prescription: `add t/prescription`

Adds a medical prescription of a patient to MedBook.

Format: `add t/prescription i/NRIC n/DRUG_NAME dt/DATE s/INSTRUCTION`

- Adding prescription with the same DRUG_NAME is allowed
- DATE should be in the format of YYYY-MM-DD

Examples:

- `add t/prescription i/S1234567L n/Amoxicillin dt/2021-09-15 s/2 tablets after meal everyday.`

Below is the list of parameters that can be used with the `add t/prescription` command.

| Parameters | Description                                                          |
|------------|:---------------------------------------------------------------------|
| `i/`       | _Required_. The NRIC associated with the patient.                    |
| `n/`       | _Required_. The drug name given to the patient.                      |
| `dt/`      | _Required_. The date of the given prescription. (Format: YYYY-MM-DD) |
| `s/`       | _Required_. The instructions given to the patient.                   |

### Viewing Prescription: `view t/prescription`

Views a medical prescription of a patient in MedBook.

Format: `view t/prescription i/NRIC`

Examples:

- `view t/prescription i/S1234567L`

### Editing Prescription: `edit`

Edits an existing prescription entry in MedBook when a list of prescription entries is displayed.
This is except for the NRIC field, which cannot be modified after the creation of the Prescription record.
At least one field must be filled for the edit command to be valid.

Format: `edit INDEX [n/DRUG_NAME] [dt/DATE] [s/INSTRUCTION]`

Examples:

- `view t/prescription i/S1234567L` followed by `edit 1 s/1 tablet everyday` updates the instruction of the first
  prescription entry displayed on the screen.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Editing a prescription will overwrite the old data.
</div>

### Adding Test Result: `add t/test`

Adds a test result taken by a patient in MedBook.

Format: `add t/test i/NRIC td/DATE mt/MEDICAL_TEST r/RESULT`

- DATE should be in the format of YYYY-MM-DD

Examples:

- `add t/test i/S1234567L td/2019-09-15 mt/X-Ray r/Broken wrist`

Below is the list of parameters that can be used with the `add t/test` command.

| Parameters | Description                                                          |
|------------|:---------------------------------------------------------------------|
| `i/`       | _Required_. The NRIC associated with the patient.                    |
| `td/`      | _Required_. The date of the test was taken. (Format: YYYY-MM-DD)     |
| `mt/`      | _Required_. The name of the medical test taken.                      |
| `r/`       | _Required_. The results of the test taken.                           |

<div style="page-break-after: always;"></div>

### Viewing Test Result: `view t/test`

Views all the test results taken by a patient in MedBook.

Format: `view t/test i/NRIC`

Examples:

- `view t/test i/S1234567L`

### Editing Test Result: `edit`

Edits an existing test result entry in MedBook when a list of test result entries is displayed.
This is except for the NRIC field, which cannot be modified after the creation of the Test Result record.
At least one field must be filled for the edit command to be valid.

Format: `edit INDEX [td/TEST_DATE] [mt/MEDICAL_TEST] [r/TEST_RESULT]`

Examples:

- `view t/test i/S1234567L` followed by `edit 1 r/Brain damage` updates the result of the first test result entry
  displayed on the screen.


<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Editing a test result will overwrite the old data.
</div>

<div style="page-break-after: always;"></div>

### Viewing Help: `help`

Shows a message explaining how to access the help page.

<img src="images/helpMessage.png" alt="Unable to load image! Try again later.">

Format: `help`


### Deleting any Entry: `delete`

Deletes a specified entry (patient, contact, medical information, ...) on the current screen from MedBook.

Format: `delete INDEX`

- You can delete the entry only if the entry is being shown on the display panel.
- For all screens apart from Summary, the index refers to the index number shown in the displayed list.
- The index must be a positive integer 1, 2, 3, …​
- For Summary screen, `delete 1` deletes the patient being viewed; other indices are invalid.

Examples:

- `view t/prescription i/S1234567L` followed by `delete 2` deletes the second prescription of the patient displayed on
  the screen.
- `view` followed by `delete 1` deletes the first patient displayed on the screen.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Deleting a patient will remove all the associated information (e.g medical info, prescriptions, consultations etc.)
</div>

### Finding any Entry: `find`

Finds a specified entry (patient, contact, medical information, ...) on the current screen from MedBook containing the
keywords you have specified.

Format: `find KEYWORD`

- You can find the entry only if the entry is being shown on the display panel.
- Finding a Patient's information is done through specifying their name as the keyword.
- Finding all other records matches your keyword with any of the fields in that record.

Examples:

- `view` followed by `find John` shows only patients whose name is John.
- `view t/test i/S1234567L` followed by `find x-ray` will filter the test results belonging to the patient with NRIC
  S1234567L to only show those which contains the keyword `x-ray`.

<div markdown="block" class="alert alert-info">
**:information_source: Info:**<br>

1. Although `NIL` is an empty placeholder for the optional fields, `find NIL` will still filter the records in
the current display to show those which contains the keyword `NIL` to accommodate for cases where the user intentionally
types `NIL` in any field.

2. `find` is not supported for a patient's summary. When viewing summary, `find` will filter the records in the list of Patients instead as this is the default behavior of `find`.

</div>

<div style="page-break-after: always;"></div>

## FAQ

<div class="card">
<div markdown="1">
<h6 markdown="1">How do I transfer my data to another computer?</h6>

**Option 1:**
By default, MedBook will always read `medbook.json` located in `[HOME_DIRECTORY of MedBook.jar]/data/`. Just move your
data to `[HOME_DIRECTORY of MedBook.jar]/data/` in your new device if you wish to import them.

**Option 2 (with encryption):**
By default, MedBook will always encrypt your data to `password.enc` in `[HOME_DIRECTORY of MedBook.jar]/data/` for
security reasons. You can move `password.enc` to `[HOME_DIRECTORY of MedBook.jar]/data/` in your new device and MedBook
will ask for your password upon launching.

</div>
</div>
<div class="card">
<div markdown="1">
<h6 markdown="1">What happens if I forget my password?</h6>
Due to security reasons, there is no way to reset your password. To continue using the application, you need to delete the 
encrypted data file (located at `[HOME_DIRECTORY of MedBook.jar]/data/password.enc`) or move it to another location. 
MedBook will start with a fresh set of data for you to start over. See the next FAQ for restoring data.
</div>
</div>
<div class="card">
<div markdown="1">
<h6 markdown="1">What happens if I accidentally remove `password.enc`?</h6>
Unfortunately, the encrypted data will be lost along with your password. However, you can restore your data if you have 
`medbook.json` with you. Make sure you place them in `[HOME_DIRECTORY of MedBook.jar]/data/` before launching the app. 
When you start MedBook again, you will be asked to set up a new password.

We highly discourage users to tamper with `password.enc` for security reasons as any encryption can be broken given
a sufficient amount of time.

</div>
</div>

<div style="page-break-after: always;"></div>

<div class="card">
<div markdown="1">
<h6 markdown="1">Can I modify `medbook.json` locally?</h6>
We highly discourage users from modifying `medbook.json` directly as any invalid input will cause MedBook to launch with
empty data and `medbook.json` will be overwritten.

If you understand what you are dealing with, you may proceed with caution. We recommend that you back up a copy of
`medbook.json` in case you accidentally corrupt the file at any point in time.

</div>
</div>

# Name: Chris Phillips
# Course: CPS 480
# Semester: Fall 2018
# Title: Expert System to Determine a Minimalist
# Description: This program is an expert system that will determine if the person answering
# the questions is what I would consider a "minimalist".
import time

# Description: This method is used by every one of the question methods. It prints out questions in a nice format.
# Paramaters: option1 and option2 are both strings that represent the answers to the question that has been posed.
# Return: This method will either return which number the user chose or continually ask for a valid input.


def question(option1, option2):
    print("1) %s" % option1)
    print("2) %s" % option2)

    userIn = int(input())
    if userIn in (1, 2):
        return userIn
    else:
        print("Not a vaild input.")
        return question(option1, option2)

# Description: This method asks a question about the user's opinion on the amount of things people should have.
# Paramaters: NA
# Return: This method will return whether the user prefers less stuff or more stuff.
#         1 == "Less stuff"
#         2 == "More stuff"


def stuff_question():
    print("What are your feelings on \"stuff\"? Do you think people should have less of it? Or more?")
    return question("Less stuff", "More stuff")


# Description: This method asks a question about if the user owns less than 50 articles of clothing
# Paramaters: NA
# Return: This method will return whether the user has more or less than 50 articles of clothing.
#         1 == "Yup"
#         2 == "Nope"
def fifty_question():
    print("Okay. Now would you say that you possess roughly less than 50 articles of clothing?")
    return question("Yup", "Nope")


# Description: This method asks a question about whether most of the user's clothing is for comfort or fashion.
# Paramaters: NA
# Return: This method will return whether the user has more clothing for comfort or fashion.
#         1 == "Comfort"
#         2 == "Fashion"
def comfort_question():
    print("Are a majority of your clothes for comfort or for fashion?")
    return question("Comfort", "Fashion")


# Description: This method asks a question whether the user keeps their possessions well organized.
# Paramaters: NA
# Return: This method will return whether the user keeps organized or not.
#         1 == "Yes"
#         2 == "No"
def organized_question():
    print("Would you say that the possessions that you do have are organized well?")
    return question("Yes", "No")


# Description: This method asks a question about the quality of their clothing
# Paramaters: NA
# Return: This method will return whether the user has high quality clothing or mainly low to moderate quality
#         1 == "High Quality"
#         2 == "Low to Moderate Quality"
def quality_question():
    print("What about quality?")
    return question("High Quality", "Low to Moderate Quality")


# Description: This method asks a question about whether the user makes environmentally conscious decisions while shopping
# Paramaters: NA
# Return: This method will return whether the user makes environmentally conscious decisions while shopping or not.
#         1 == "Yes"
#         2 == "No"
def environment_question():
    print("Are you making environmentally conscious choices in your shopping?")
    return question("Yes", "No")


# Description: This method asks a question about if the user gains happiness from all of their possessions.
# Paramaters: NA
# Return: This method will return whether the user is fully happy with all of their items or not.
#         1 == "Yeah"
#         2 == "No, only some"
def happiness_question():
    print("Do all the items that you own bring you happiness?")
    return question("Yeah", "No, only some")


# Description: This method asks a question about if the user has simple work and personal spaces
# Paramaters: NA
# Return: This method will return whether the user has simple spaces or not.
#         1 == "Yes"
#         2 == "No"
def space_question():
    print("Are your living and working spaces simple?")
    return question("Yes", "No")


# Description: This method asks a question about if the user's spaces are functional
# Paramaters: NA
# Return: This method will return whether the user has functional spaces or not.
#         1 == "Yes"
#         2 == "No"
def functional_question():
    print("Are your living and working spaces functional?")
    return question("Yes", "No")


# Description: This method asks a question about if all of the user's possessions are necessary in their lives
# Paramaters: NA
# Return: This method will return whether all these possessions are necessary or not.
#         1 == "Yes"
#         2 == "No"
def necessary_question():
    print("Are the possessions in your spaces necessary for your well-being?")
    return question("Yes", "No")


# Description: This method asks a question about if the user considers themselves financially responsible
# Paramaters: NA
# Return: This method will return whether the user thinks they're financially responsible or not.
#         1 == "Yes"
#         2 == "No"
def financial_question():
    print("Would you consider yourself someone that makes financially responsible decisions?")
    return question("Yes", "No")


# Description: This method asks a question about if the user is open to changing their lifestyle
# Paramaters: NA
# Return: This method will return whether the user is open to that change or not
#         1 == "Sure"
#         2 == "Nah"
def change_question():
    print("Are you open to changing your lifestyle up a bit?")
    return question("Sure", "Nah")


# Description: This method asks a question about if the user prefers quality over quantity
# Paramaters: NA
# Return: This method will return whether the user prefers quality or quantity
#         1 == "Quality"
#         2 == "Quantity"
def qoq_question():
    print("Given the choice, would you choose quality or quantity?")
    return question("Quality", "Quantity")


# Description: This method asks a question about if the user prefers fewer, more intense friendships or more, les meaningful friendships
# Paramaters: NA
# Return: This method will return whether the user has more surface level friendships or deep friendships
#         1 == "Small group"
#         2 == "Large group"
def people_question():
    print("Do you try to keep a close group of friends or a constantly growing group of friends?")
    return question("Small group", "Large group")


# Description: This method asks a question about if the user values experiences over material goods
# Paramaters: NA
# Return: This method will return if the user prefers good experiences over material goods
#         1 == "Yes"
#         2 == "No"
def experiences_question():
    print("Do you value good experiences over material goods?")
    return question("Yes", "No")

# Description: This method asks a question about if the user likes to buy things at yard sales
# Paramaters: NA
# Return: This method will return whether the user enjoys buying things at yard sales
#         1 == "Yes"
#         2 == "No"


def yard_sales_question():
    print("Do you enjoy buying things at yard sales?")
    return question("Yes", "No")


# Description: This method asks a question about if the user has a high or low presence on social media
# Paramaters: NA
# Return: This method will return whether the user has a strong presence or weak
#         1 == "Strong"
#         2 == "Weak"
def social_media_question():
    print("Do you have a strong or weak presence on social media?")
    return question("Strong", "Weak")


# Description: This method will print out that the user is indeed a minimalist
# Paramaters: NA
# Return: NA
def minimalist():
    print("Yes, you're a minimalist!")


# Description: This method will print out that the user might not be a minimalist
# Paramaters: NA
# Return: NA
def not_minimalist():
    print("Hmm, I don't think you're a minimalist.")


# Description: This method contains the logic all questions that follow the "Space" question.
# Paramaters: NA
# Return: This method will return whether the user is a minimalist or not
def full_space_question():
    if space_question() == 1:
        if financial_question() == 1:
            minimalist()
        else:
            not_minimalist()
    else:
        if functional_question() == 1:
            if financial_question() == 1:
                minimalist()
            else:
                not_minimalist()
        else:
            if necessary_question() == 1:
                if financial_question() == 1:
                    minimalist()
                else:
                    not_minimalist()
            else:
                not_minimalist()


# Description: This method contains the logic for the entire "left side" of the question tree
# Paramaters: NA
# Return: This method will return whether the user is a minimalist or not
def left_side():
    if fifty_question() == 1:
        if comfort_question() == 1:
            full_space_question()
        else:
            if quality_question() == 1:
                full_space_question()
            else:
                if environment_question() == 1:
                    full_space_question()
                else:
                    if happiness_question() == 1:
                        full_space_question()
                    else:
                        not_minimalist()
    else:
        if organized_question() == 1:
            if quality_question() == 1:
                full_space_question()
            else:
                if environment_question() == 1:
                    full_space_question()
                else:
                    if happiness_question() == 1:
                        full_space_question()
                    else:
                        not_minimalist()
        else:
            not_minimalist()


# Description: This method contains the logic for the entire "right side" of the question tree
# Paramaters: NA
# Return: This method will return whether the user is a minimalist or not
def right_side():
    if qoq_question() == 1:
        full_space_question()
    else:
        if organized_question() == 1:
            if quality_question() == 1:
                full_space_question()
            else:
                if environment_question() == 1:
                    full_space_question()
                else:
                    if happiness_question() == 1:
                        full_space_question()
                    else:
                        not_minimalist()
        else:
            if people_question() == 1:
                full_space_question()
            else:
                if experiences_question() == 1:
                    full_space_question()
                else:
                    if environment_question() == 1:
                        full_space_question()
                    else:
                        if comfort_question() == 1:
                            full_space_question()
                        else:
                            if yard_sales_question() == 2:
                                full_space_question()
                            else:
                                if social_media_question() == 2:
                                    full_space_question()
                                else:
                                    if financial_question() == 1:
                                        minimalist()
                                    else:
                                        not_minimalist()


# Introduction to program
print("It's time to find out if you're a minimalist! "
      "By answering just a few simple questions, you'll be able to understand yourself a little more deeply.\n")

print("We'll start out with a question regarding something that a lot of minimalists regard as foundational.\n")

# All the logic, using the methods defined above
if stuff_question() == 1:
    left_side()
else:
    if change_question() == 1:
        left_side()
    else:
        right_side()

time.sleep(5)

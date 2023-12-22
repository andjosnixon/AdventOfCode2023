# trying this challenge in python instead of Java
import pandas as pd
import numpy as np
pd.options.mode.chained_assignment = None  # default='warn'

# attempts
# 46485515 too low
# 253205868 correct. Damn, that was a hard one


def detect_repeated_characters(string):
    chars = {}
    for char in string:
        if char in chars:
            chars[char] += 1
        else:
            chars[char] = 1

    return sorted(chars.values(), reverse=True)


def sort_hands(df_array):
    df_array['Converted'] = "0x"
    df_array['IntegerValue'] = 0
    for i in df_array['Hand'].index:
        for j in df.loc[i, 'Hand']:
            match j:
                case "2":
                    df_array.loc[i, 'Converted'] = df_array.loc[i, 'Converted'] + "2"
                case "3":
                    df_array.loc[i, 'Converted'] = df_array.loc[i, 'Converted'] + "3"
                case "4":
                    df_array.loc[i, 'Converted'] = df_array.loc[i, 'Converted'] + "4"
                case "5":
                    df_array.loc[i, 'Converted'] = df_array.loc[i, 'Converted'] + "5"
                case "6":
                    df_array.loc[i, 'Converted'] = df_array.loc[i, 'Converted'] + "6"
                case "7":
                    df_array.loc[i, 'Converted'] = df_array.loc[i, 'Converted'] + "7"
                case "8":
                    df_array.loc[i, 'Converted'] = df_array.loc[i, 'Converted'] + "8"
                case "9":
                    df_array.loc[i, 'Converted'] = df_array.loc[i, 'Converted'] + "9"
                case "T":
                    df_array.loc[i, 'Converted'] = df_array.loc[i, 'Converted'] + "A"
                case "J":
                    df_array.loc[i, 'Converted'] = df_array.loc[i, 'Converted'] + "B"
                case "Q":
                    df_array.loc[i, 'Converted'] = df_array.loc[i, 'Converted'] + "C"
                case "K":
                    df_array.loc[i, 'Converted'] = df_array.loc[i, 'Converted'] + "D"
                case "A":
                    df_array.loc[i, 'Converted'] = df_array.loc[i, 'Converted'] + "E"
        df_array.at[i, 'IntegerValue'] = int(df_array.at[i, 'Converted'], 16)
    return df_array.sort_values('IntegerValue')


# Read in data, save to dataframe
read_data = []
file = open('/Users/nixonaj1/AdventOfCode2023/src/Day07/input07.txt', 'r')
for i in file:
    parts = i.strip().split(" ")
    read_data.append([i.strip().split(" ")[0], int(i.strip().split(" ")[1])])
file.close()

# all hands get passed in here, and they get classified
df = pd.DataFrame(read_data, columns=['Hand', 'Bid'])
df['Classification'] = 0
line = 0
for i in df['Hand']:
    x = detect_repeated_characters(i)
    if x[0] == 5:                           # five of a kine
        df.loc[line, 'Classification'] = 7
    elif x[0] == 4:                         # four of a kind
        df.loc[line, 'Classification'] = 6
    elif x[0] == 3 and x[1] == 2:           # full house
        df.loc[line, 'Classification'] = 5
    elif x[0] == 3:                         # three of a kind
        df.loc[line, 'Classification'] = 4
    elif x[0] == 2 and x[1] == 2:           # two pair
        df.loc[line, 'Classification'] = 3
    elif x[0] == 2:                         # one pair
        df.loc[line, 'Classification'] = 2
    else:
        df.loc[line, 'Classification'] = 1
    line += 1

bid_calculation = 0

bidsInOrder = []


for i in range(1, 8):
    thisHandType = df[df['Classification'] == i]
    thisHandType = sort_hands(thisHandType)
    thisHandType = thisHandType.reset_index(drop=True)
    bidsInOrder = np.concatenate([bidsInOrder, np.array(thisHandType['Bid'].values)])

for j in range(bidsInOrder.size):
    bid_calculation = bid_calculation + (j+1)*bidsInOrder[j]

print(bid_calculation)

################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../CalcExpression.c \
../CashRegister.c \
../DigitFrequency.c \
../DigitFrequencyArray.c \
../Lab1-CBasics.c \
../Lab2-StringRepresentation.c \
../Lab3-BranchesAndLoops.c \
../Lab5-Pointers.c \
../LabArrayPtr.c \
../LabFunPtr.c \
../LabFunTempConvRand.c \
../QuadraticEquation.c \
../SelectionSort.c \
../Statistics.c \
../StringCopy.c \
../mymath.c \
../myutil.c 

OBJS += \
./CalcExpression.o \
./CashRegister.o \
./DigitFrequency.o \
./DigitFrequencyArray.o \
./Lab1-CBasics.o \
./Lab2-StringRepresentation.o \
./Lab3-BranchesAndLoops.o \
./Lab5-Pointers.o \
./LabArrayPtr.o \
./LabFunPtr.o \
./LabFunTempConvRand.o \
./QuadraticEquation.o \
./SelectionSort.o \
./Statistics.o \
./StringCopy.o \
./mymath.o \
./myutil.o 

C_DEPS += \
./CalcExpression.d \
./CashRegister.d \
./DigitFrequency.d \
./DigitFrequencyArray.d \
./Lab1-CBasics.d \
./Lab2-StringRepresentation.d \
./Lab3-BranchesAndLoops.d \
./Lab5-Pointers.d \
./LabArrayPtr.d \
./LabFunPtr.d \
./LabFunTempConvRand.d \
./QuadraticEquation.d \
./SelectionSort.d \
./Statistics.d \
./StringCopy.d \
./mymath.d \
./myutil.d 


# Each subdirectory must supply rules for building sources it contributes
%.o: ../%.c
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C Compiler'
	gcc -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '



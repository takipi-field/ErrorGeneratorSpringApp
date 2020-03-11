#!/bin/bash

# Set the Host name where tomcat and the application are running.
HOST=ec2-52-14-26-65.us-east-2.compute.amazonaws.com

# The URL for the transaction that will be tested.
# This should not normally need to be changed unless you modify the code.
URLPATH=ErrorSpringApp/InvokeError

# The duration of a single test run in seconds.
# OverOps should run at least 10 minutes to get a good average reading.
DURATION=1200

# Set the number of concurrent threads / simulated users
# Adjust it upwards until you achieve the desired TPM levels.
# This is an array that can have 1 or more values.  A full test will be
# executed for each unique thread count setting.
NUM_THREADS=(
50
)

# Array of error rates as a percentage of error transactions. 
# The array can have 1 or more values.  A full test will be 
# executed for each unique error rate
ERR_RATE=(
1
3
5
10
20
)

# An Array of TPM values in decimal
# NOTE:  This is a JMeter numeric argument and the demimal (x.0) is mandatory!!
#
# The array can have 1 or more values.  A full test will be
# executed for each unique TPM value.
TPM=(
5000.0
10000.0
15000.0
20000.0
25000.0
30000.0
)

# The following executes one jmeter test run for each of the variables set above.
# It's a nested loop that executes for each combination of
# NUM_THREADS - number of concurrent threads
# ERR_RATE - error rate as a percentage
# TPM - transactions per minute
#
# Each test run will execute for the number of seconds specified in DURATION.

for nt in ${NUM_THREADS[@]}; do
	for er in ${ERR_RATE[@]}; do
		for tpm in ${TPM[@]}; do
			printf "\n"
			let no_er=100-$[er]

			echo jmeter.sh -n -t OverOps_Spring_Boot_Test.jmx -Jnum_threads=$nt -Jno_err_rate=$no_er -Jerr_rate=$er -Jtpm=$tpm -Jhost=${HOST} -Jpath=${URLPATH} -Jduration=${DURATION}

			jmeter.sh -n -t OverOps_Spring_Boot_Test.jmx \
				-Jnum_threads=$nt \
				-Jno_err_rate=$no_er \
				-Jerr_rate=$er \
				-Jtpm=$tpm \
				-Jhost=${HOST} \
				-Jpath=${URLPATH} \
				-Jduration=${DURATION}
			printf "\n"
		done
	done
done

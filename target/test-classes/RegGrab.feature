Feature: RegGrab
  Program will pull registration plates from car_input.txt and return details in car_output.txt

  Scenario: car_input.txt has 3 reg plates
    Given car_input.txt contains SG18HTN, DN09HRM and BW57BOF
    When I run RegistrationGrab.java
    Then car_output.txt should contain reg plates and details

#    Given car_input.txt contains blah blah blah
#    When I run grabRegistration
#    Then should return blah blah blah
#
#    Given grabRegistration returned blah blah blah
#    When I run getData
#    Then should return List of data
#
#    Given
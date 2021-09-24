Feature: RegGrab
  Program will pull registration plates from car_input.txt and return details in car_output.txt

  Scenario: car_input.txt has 3 reg plates
    Given car_input.txt contains SG18HTN, DN09HRM and BW57BOF
    When I run RegistrationGrab.java
    Then car_output.txt should contain reg plates and details

    Given car_input.txt has SG18HTN, DN09HRM and BW57BOF
    When I run grabRegistration
    Then should return SG18HTN, DN09HRM and BW57BOF

    Given grabRegistration returned SG18HTN, DN09HRM and BW57BOF
    When I run getData
    Then should return list of data
#
#    Given getData returned blah blah blah
#    When I run getOutput
#    Then should return text output
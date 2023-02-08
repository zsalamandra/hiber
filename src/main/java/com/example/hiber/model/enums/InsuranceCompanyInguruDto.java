package com.example.hiber.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InsuranceCompanyInguruDto {

    ALFA(1),
    IGS(3),
    RGS(5),
    SOGLASIE(7),
    MAKS(10),
    UGORIA(25),
    ZETTA(27),
    RESO(32),
    VSK(33),
    RENESSANS(36),
    INTOUCH(39),
    SOGAZ(46),
    GAIDE(47),
    OSK(61),
    ABSOLUT(75),
    ASTRO_VOLGA(98),
    TINKOFF(107),
    VERNA(125),
    EUROINS(127),
    MAFI(142),
    ASKO(72),
    GELIOS(52),
    SBER(144),
    ENERGY(145),
    SPASSKY(34);

    private final int value;
}


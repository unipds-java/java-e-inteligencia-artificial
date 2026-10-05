"use client";

import { FC } from "react";

type MeuNomeProps = { name: string; age: number; birthDate: Date };

export const MeuNome: FC<MeuNomeProps> = ({ name, age, birthDate }) => (
  <p>
    Sou o {name}, tenho {age} anos porque nasci no dia{" "}
    {birthDate.toLocaleDateString("pt-BR")}
  </p>
);

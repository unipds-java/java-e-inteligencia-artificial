import ContadorProvider from "@/context/aula-2/ContadorContext";

export default function Layout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return <ContadorProvider>{children}</ContadorProvider>;
}

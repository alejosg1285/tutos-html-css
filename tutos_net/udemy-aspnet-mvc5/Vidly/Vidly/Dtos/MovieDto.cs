using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;
using Vidly.Models;

namespace Vidly.Dtos
{
    public class MovieDto
    {
        public int Id { get; set; }

        [Required(ErrorMessage = "El nombre la pelicula es requerido.")]
        [StringLength(255)]
        public string Name { get; set; }
        
        [Required]
        public byte GenreId { get; set; }

        public GenreDto Genre { get; set; }
        public DateTime DateAdded { get; set; }
        [Display(Name = "Release Date")]
        public DateTime DateRelease { get; set; }
        
        [Range(1, 20)]
        public int NumberInStock { get; set; }
    }
}